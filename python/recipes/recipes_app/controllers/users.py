from recipes_app import app
from flask import render_template, redirect, request,flash, session
from recipes_app.models import user

@app.route('/')
def login_register_page():
    if session.get('user_id'):
        return redirect('/recipes')
    return render_template('index.html', data = None)

@app.route('/register', methods=['POST'])
def register_user():
    if user.User.create_user(request.form):
        return redirect('/recipes')
    return render_template('index.html',data = request.form)

@app.route('/login', methods=["POST"])
def login():
    if user.User.login_user(request.form):
        return redirect('/recipes')
    return render_template('index.html',data = request.form)

@app.route('/logout')
def logout():
    session.clear()
    return redirect('/')

@app.route('/users/<int:id>')
def get_single_user(id):
    if session.get('user_id'):
        this_user = user.User.get_user_by_id(id)
        if this_user:
            return render_template('users/show_user.html', user=this_user)