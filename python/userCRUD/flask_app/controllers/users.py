from flask import render_template,redirect,request
from flask_app import app
# import the class from friend.py
from flask_app.models.user import User

@app.route ('/users/registration',methods = ['POST'])
def register_user():
    if not User.validate_user_reg_data(request.form):
        return redirect ('/')
    data = {
        "fname": request.form["fname"],
        "lname" : request.form["lname"],
        "email" : request.form["email"]
    }
    new_user = User.save(data)
    return redirect('/users')

@app.route('/')
def index():
    users = User.get_all()
    print(users)
    return render_template("index.html", all_users = users)

@app.route('/users')
def get_single_user():
    users = User.get_all()
    return render_template('redirect.html', all_users = users)

@app.route('/users/view/<int:id>')
def get_one_user(id):
    this_user = User.get_single_id(id)
    return render_template('show_user.html', user = this_user)

@app.route('/users/edit/<int:id>', methods=['GET', 'POST'])
def update_user(id):
    if request.method == "GET":
        this_user = User.get_single_id(id)
        return render_template('edit.html', user = this_user)
    edited_data = {
        'id': id,
        'first_name': request.form['first_name'],
        'last_name': request.form['last_name'],
        'email': request.form['email'],
    }
    user = User.update_user(edited_data)
    return redirect(f'/users/view/{id}')





@app.route('/users/delete/<int:id>')
def delete_user(id):
    User.delete_user(id)
    return redirect('/users')