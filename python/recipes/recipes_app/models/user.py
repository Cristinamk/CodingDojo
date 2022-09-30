from multiprocessing import parent_process
from recipes_app.config.mysqlconnection import connectToMySQL, db
from recipes_app.models import recipe
from flask import flash, session
import re
from recipes_app import app
from flask_bcrypt import Bcrypt
bcrypt = Bcrypt(app)


# PATTERN VALIDATORS
EMAIL_REGEX = re.compile(r'^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9.+_-]+\.[a-zA-Z]+$')
PWD_REGEX = re.compile(r"^(?=.*[\d])(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*?])[\w\d!@#$%^&*?]{6,12}$")


class User:
    def __init__(self,data):
        self.id = data['id']
        self.first_name = data['first_name']
        self.last_name = data['last_name']
        self.email = data['email']
        self.password = data['password']
        self.created_at = data['created_at']
        self.updated_at = data['updated_at']
        self.recipes = []

    def print_full_name(self):
        return f'{self.first_name} {self.last_name}'



    #  CREATE SQL
    @classmethod
    def create_user(cls,form_data):
        if not User.vaildate_register_form(form_data):
            return False
        data = User.parse_form_data(form_data)
        query = '''
        INSERT INTO users 
        (first_name,last_name,email,password)
        VALUES
        (%(first_name)s,%(last_name)s,%(email)s,%(password)s);
        '''
        user_id = connectToMySQL(db).query_db(query,data)
        session['user_id'] = user_id
        session['user_name'] = f'{data["first_name"]} {data["last_name"]}'
        return True


    # READ SQL
    @classmethod
    def get_user_by_id(cls,id):
        data = {'id':id}
        query = '''
        SELECT * FROM users
        LEFT JOIN recipes
        ON users.id = recipes.user_id
        WHERE users.id = %(id)s;
        '''
        results = connectToMySQL(db).query_db(query,data)
        this_user = cls(results[0])
        for this_recipe in results:
            data = {
                'id': this_recipe['recipes.id'],
                'user_id': this_recipe['user_id'],
                'name': this_recipe['name'],
                'description': this_recipe['description'],
                'instructions': this_recipe['instructions'],
                'date_cooked': this_recipe['date_cooked'],
                'is_under_30_mins': this_recipe['is_under_30_mins'],
                'created_at': this_recipe['recipes.created_at'],
                'updated_at': this_recipe['recipes.updated_at']
            }
            this_user.recipes.append(recipe.Recipe(data))
        return this_user

    @classmethod
    def get_user_be_email(cls, email):
        data = {'email':email}
        query = '''
        SELECT *
        FROM users
        WHERE email = %(email)s;
        '''
        result = connectToMySQL(db).query_db(query,data)
        if result:
            result = cls(result[0])
        return result



    # UPDATE METHOD
    @classmethod
    def update_user(cls,data):
        data = User.parse_form_data(data)
        data['id'] = session['user_id']
        query = '''
        UPDATE users
        SET 
        first_name = %(first_name)s,
        last_name = %(last_name)s,
        email = %(email)s,
        password = %(password)s
        WHERE id = %(id)s;
        '''
        return connectToMySQL(db).query_db(query,data)



    @classmethod
    def delete_user(cls,id):
        data = {'id':id}
        query = '''
        DELETE FROM users
        WHERE id = %(id)s;
        '''
        return connectToMySQL(db).query_db(query,data)



    # STATIC METHODS
    @staticmethod
    def parse_form_data(data):
        parsed_data = {}
        parsed_data['first_name'] = data['first_name'].strip()
        parsed_data['last_name'] = data['last_name'].strip()
        parsed_data['email'] = data['email'].lower().strip()
        parsed_data['password'] = bcrypt.generate_password_hash(data['password'])
        return parsed_data

    @staticmethod
    def vaildate_register_form(data):
        is_valid = True
        if User.get_user_be_email(data):
            flash('Sorry this email is already in use..')
            is_valid = False
        if len(data['first_name']) < 2:
            flash('First name must be 2 or characters')
            is_valid = False
        if len(data['last_name']) < 2:
            flash('Last name must be 2 or characters')
            is_valid = False
        if not EMAIL_REGEX.match(data['email']):
            flash('Please enter a valid email...')
            is_valid = False
        if not PWD_REGEX.match(data['password']):
            flash('''Password must have capitol/lowercase letters,
                    a number, and a specail charater''')
            is_valid = False
        if data['password'] != data['confirm_password']:
            flash('Passwords do not match...')
            is_valid = False
        return is_valid

    @staticmethod
    def login_user(data):
        this_user = User.get_user_be_email(data['email'])
        if this_user:
            if bcrypt.check_password_hash(this_user.password, data['password']):
                session['user_id'] = this_user.id
                session['user_name'] = f'{this_user.first_name} {this_user.last_name}'
                return True
        flash('Invalid email/password')
        return False
