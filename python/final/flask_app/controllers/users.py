from flask_app import app
from flask import render_template,redirect,request,session
from flask_app.models import user


@app.route('/')
def index():
    return render_template ('index.html', data = None, login_data=None)

#create users
@app.route('/register', methods = ['POST'])
def register_user():
    if user.User.create_user(request.form):
        return redirect ('/dashboard')# redirect to where the project tlles us 
    return render_template ('index.html',data = request.form, login_data=None)

@app.route('/login' ,methods = ['POST'])
def login():
    if user.User.login(request.form):
        return redirect('/dashboard') # redirect to where the project tlles us 
    return render_template('index.html' ,data = None, login_data = request.form)

@app.route('/logout')
def logout():
    session.clear()
    return redirect('/')

