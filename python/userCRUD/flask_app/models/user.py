# import the function that will return an instance of a connection
from flask_app.config.mysqlconnection import connectToMySQL
from flask import flash
import re
# model the class after the friend table from our database
class User:
    def __init__( self , data ):
        self.id = data['id']
        self.first_name = data['first_name']
        self.last_name = data['last_name']
        self.email = data['email']
        self.created_at = data['created_at']
        self.updated_at = data['updated_at']
    # Now we use class methods to query our database

    @classmethod
    def create_user(cls,data):
        if not cls.validate_user_reg_data(data):
            return False
        parsed_data = cls.parse_reg_data(data)
        query = """INSERT INTO users ( first_name , last_name , email , created_at, updated_at ) 
        VALUES ( %(fname)s , %(lname)s , %(email)s , NOW() , NOW() );"""
        user_id = connectToMySQL('users_schema').query_db( query, parsed_data )
        return True

    
    @classmethod
    def get_all(cls):
        query = "SELECT * FROM users;"
        # make sure to call the connectToMySQL function with the schema you are targeting.
        results = connectToMySQL('users_schema').query_db(query)
        # Create an empty list to append our instances of friends
        users = []
        # Iterate over the db results and create instances of friends with cls.
        for user in results:
            users.append( cls(user) )
        return users
    @classmethod
    def save(cls, data ):
        query = """INSERT INTO users ( first_name , last_name , email , created_at, updated_at ) 
        VALUES ( %(fname)s , %(lname)s , %(email)s , NOW() , NOW() );"""
        # data is a dictionary that will be passed into the save method from server.py
        return connectToMySQL('users_schema').query_db( query, data )

    @classmethod
    def get_single_user(cls,email):
        data = {'email':email}
        query  = "SELECT * FROM users WHERE email = %(email)s;"
        result = connectToMySQL('users_schema').query_db(query,data)
        if len(result) < 1:
            return False
        return cls(result[0])
    
    @classmethod
    def get_single_id(cls,id):
        data = {'id':id}
        query  = "SELECT * FROM users WHERE id = %(id)s;"
        result = connectToMySQL('users_schema').query_db(query,data)
        return cls(result[0])


    @classmethod
    def update_user(cls,data):
        query = """ UPDATE users SET first_name=%(first_name)s, 
        last_name=%(last_name)s, email=%(email)s
        WHERE id = %(id)s;"""
        return  connectToMySQL('users_schema').query_db(query,data)
    
    @classmethod
    def delete_user(cls,id):
        data = {'id':id }
        query = """DELETE FROM users WHERE id = %(id)s;
        """
        return connectToMySQL('users_schema').query_db( query, data )


    @staticmethod
    def validate_user_reg_data(data):
        EMAIL_REGEX = re.compile(r'^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9._-]+\.[a-zA-Z]+$')
        is_valid = True # we assume this is true
        if len(data['fname']) < 2:
            flash("First name must be at least 2 characters.")
            is_valid = False
        if len(data['lname']) < 3:
            flash("Last name must be at least 3 characters.")
            is_valid = False
        if len (data['email']) < 1:
            flash("Email can't be empty .")
            is_valid = False
        if not EMAIL_REGEX.match(data['email']):
            flash("Use a valid email address")
            is_valid = False
        if User.get_single_user(data['email'].lower()):
            flash("That email is in use")
            is_valid = False
        return is_valid

    # @staticmethod
    # def parse_reg_data(data):
    #     parsed_data = {}
    #     parsed_data['first_name']= data['first_name']
    #     parsed_data['last_name']= data['last_name']
    #     parsed_data['email']= data ['email'].lower()
    #     return parsed_data