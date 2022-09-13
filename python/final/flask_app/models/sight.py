from flask_app.config.mysqlconnection import connectToMySQL
from flask import flash
from flask import flash, session
from flask_app.models import user


class Sight:
    db = "finalproject"
    def __init__(self, data):
        self.id = data["id"]
        self.user_id = data["user_id"]
        self.location = data["location"]
        self.what_happend = data["what_happend"]
        self.date_sight = data["date_sight"]
        self.num_sasq = data["num_sasq"]
        self.created_at = data["created_at"]
        self.updated_at = data["updated_at"]
        self.user_id = None

    @classmethod
    def new_sight(cls, form_data):
        if not cls.validate_sight_form(form_data):
            return False
        data = cls.parse_sight_data(form_data)
        query = '''
        INSERT INTO sight
        (user_id,location,what_happend,date_sight,num_sasq)
        VALUES 
        (%(user_id)s,%(location)s,%(what_happend)s,
        %(date_sight)s,%(num_sasq)s);
        '''
        connectToMySQL(cls.db).query_db(query,data)
        return True

    @classmethod
    def get_all(cls):
        query = '''
        SELECT * FROM sight
        JOIN users
        ON sight.user_id = users.id;
        '''
        results = connectToMySQL(cls.db).query_db(query)
        all_sights = []
        for sight in results:
            sight_data = {
                'id': sight['id'],
                'user_id': sight['user_id'],
                'location': sight['location'],
                'what_happend': sight['what_happend'],
                'date_sight': sight['date_sight'],
                'num_sasq': sight['num_sasq'],
                'created_at': sight['created_at'],
                'updated_at': sight['updated_at'],
            }
            this_sight = cls(sight_data)
            user_data = cls.create_user_data(sight)
            this_sight.user = user.User(user_data)
            all_sights.append(this_sight)
        return all_sights

    @classmethod
    def get_one(cls,id):
        query = '''
        SELECT * FROM sight
        JOIN users
        ON sight.user_id = users.id 
        WHERE sight.id = %(id)s;
        '''
        results = connectToMySQL(cls.db).query_db(query,{"id":id})
        if len(results) == 0:
            return None
        sight = results[0]
        sight_data = {
            'id': sight['id'],
            'user_id': sight['user_id'],
            'location': sight['location'],
            'what_happend': sight['what_happend'],
            'date_sight': sight['date_sight'],
            'num_sasq': sight['num_sasq'],
            'created_at': sight['created_at'],
            'updated_at': sight['updated_at'],
            }
        this_sight = cls(sight_data)
        user_data = cls.create_user_data(sight)
        this_sight.user = user.User(user_data)
        return this_sight

    @classmethod
    def update_one(cls,form_data):
        query = '''
        UPDATE sights
        SET
        location = %(location)s,
        what_happend = %(what_happend)s,
        date_sight = %(date_sight)s,
        num_sasq = %(num_sasq)s,
        WHERE id = %(id)s;
        '''
        connectToMySQL(cls.db).query_db(query,form_data)
        return True
        

    @classmethod
    def delete_one(cls,id):
        data = {"id":id}
        query = '''
        DELETE FROM sight
        WHERE id = %(id)s;
        '''
        return connectToMySQL(cls.db).query_db(query,data)




    @staticmethod
    def validate_sight_form(form_data):
        is_valid = True
        if len(form_data['location']) < 1:
            flash('Location must be at least 1 charaters')
            is_valid = False
        if len(form_data['what_happend']) < 3:
            flash('Desription must be at least 3 charaters')
            is_valid = False
        if int(form_data['num_sasq']) < 0:
            flash('Desription must be at least value of 1')
        return is_valid


    @staticmethod
    def parse_sight_data(form_data):
        parsed_data = {}
        parsed_data['user_id'] = session['user_id']
        parsed_data['location'] = form_data['location']
        parsed_data["what_happend"] = form_data["what_happend"]
        parsed_data["date_sight"] = form_data["date_sight"]
        parsed_data["num_sasq"] = form_data["num_sasq"]
        return parsed_data

    @staticmethod
    def create_user_data(data):
        user_data = {
            'id': data['users.id'],
            'first_name': data['first_name'],
            'last_name': data['last_name'],
            'email': data['email'],
            'password': data['password'],
            'created_at': data['users.created_at'],
            'updated_at': data['users.updated_at']
        }
        return user_data