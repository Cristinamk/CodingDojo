from flask_app import app
from flask import render_template,redirect,request,session,flash
from flask_app.models import sight


@app.route('/dashboard') 
def dashboard():
    data = sight.Sight.get_all()
    return render_template ('dashboard.html',all_sightings = data)

@app.route('/new/sighting/post', methods = ['POST'])
def newsight():
    if sight.Sight.new_sight(request.form):
        return redirect ('/dashboard')# redirect to where the project tlles us 
    return render_template ('index.html',data = request.form, login_data=None)

@app.route('/new/sighting')
def addsight():
    return render_template ('add.html')

@app.route('/show/<int:id>')
def showsight(id):
    data = sight.Sight.get_one(id)
    if data == None: #in case someone is trying to break me giyhub
        return redirect ('/dashboard')
    else:
        return render_template ('show.html',sighting = data )

@app.route('/edit/<int:id>')
def editsight(id):
    data = sight.Sight.get_one(id)
    return render_template ('edit.html',sighting = data )



@app.route('/update/sighting/', methods = ['POST'])
def update():
    sight.Sight.update_one(request.form)
    return redirect ('/dashboard')

@app.route('/delete/<int:id>')
def deletesight(id):
    sight.Sight.delete_one(id)
    return redirect ('/dashboard')