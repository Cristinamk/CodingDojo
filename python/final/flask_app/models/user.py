from flask_app.config.mysqlconnection import connectToMySQL
from flask_app import app
from flask_bcrypt import Bcrypt        
bcrypt = Bcrypt(app) 
from flask import flash,session
import re


class User:
    db = "finalproject"
    def __init__( self , data ):
        self.id = data['id']
        self.first_name = data['first_name']
        self.last_name = data['last_name']
        self.email = data['email']
        self.created_at = data['created_at']
        self.updated_at = data['updated_at']
        self.password = data['password']
        self.ideas =[]
    
    def get_name(self):
        return f'{self.first_name} {self.last_name}'
    
    @classmethod
    def create_user(cls,data):
        if not cls.validate_user_reg_data(data):
            return False
        parsed_data = cls.parse_reg_data(data)
        query = """
        INSERT INTO users (first_name, last_name,email,password)
        VALUES (%(first_name)s ,%(last_name)s ,%(email)s ,%(password)s );
        """
        user_id = connectToMySQL(cls.db).query_db(query,parsed_data)
        session['user_id'] = user_id
        session['first_name'] = f' {data["first_name"]}'
        return True

    @classmethod
    def all_users(cls):
        query = """SELECT * FROM users;"""
        results = connectToMySQL(cls.db).query_db( query)
        all_users = []
        for person in results:
            all_users.append(cls(person))
        return all_users
    
    @classmethod
    def get_user_by_email(cls,data):
        data ={'email': data}
        query = """SELECT * FROM users WHERE email = %(email)s;"""
        results = connectToMySQL(cls.db).query_db( query, data )
        if results :
            this_user = cls(results[0])
            return this_user
        return False

    @classmethod
    def get_one(cls, data):
        query = "SELECT * FROM users where id = %(id)s;"
        results = connectToMySQL(cls.db).query_db(query, data)
        this_user =  cls(results[0])
        return this_user

        #add class association 
    # @join query to incorp. other tables
    # loop thrugh results 
    # create instances of other tables
    # add instace to user.ideas 
    #import other class at top of file 

    #validation user
    @staticmethod
    def validate_user_reg_data(data):
        PWD_REGEX = re.compile(r"^(?=.*[\d])(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*?])[\w\d!@#$%^&*?]{6,12}$")
        EMAIL_REGEX = re.compile(r'^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9._-]+\.[a-zA-Z]+$')
        is_valid = True
        if len(data['first_name']) < 2:
            flash("First name must be at least 2 characters")
            is_valid = False
        if len(data['last_name']) < 2:
            flash("Last name must be at least 2 characters")
            is_valid = False
        if not EMAIL_REGEX.match(data['email']) :
            flash("Use a valid email address")
            is_valid = False
        if User.get_user_by_email(data['email']):
            flash("Thi email is already in use")
            is_valid = False
        if not PWD_REGEX.match(data['password']):
            flash("Password must include,one upper/lower case letter,one digit and one special charater")
            is_valid = False
        if data['password'] != data['confirm_password']:
            flash("Password do not match")
            is_valid = False
        return is_valid
    
    @staticmethod
    def parse_reg_data(data):
        parsed_data = {}
        parsed_data['first_name'] = data['first_name']
        parsed_data['last_name'] = data['last_name']
        parsed_data['password'] = bcrypt.generate_password_hash(data['password'])
        parsed_data['email'] = data ['email']
        return parsed_data

    @staticmethod
    def login(data):
        this_user = User.get_user_by_email(data['email'])
        if this_user:
            if bcrypt.check_password_hash(this_user.password, data ['password']):
                session['user_id'] = this_user.id
                session['first_name'] = this_user.first_name
                return True
            flash ('Your login info is incorrect')
            return False
