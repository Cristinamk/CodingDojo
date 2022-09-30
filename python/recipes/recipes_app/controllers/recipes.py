from recipes_app import app
from flask import render_template,redirect,request,flash,session
from recipes_app.models import recipe
from datetime import date
today = date.today()

@app.route('/recipes')
def show_all_recipies():
    if session.get('user_id'):
        all_recipes = recipe.Recipe.get_all_recipes()
        return render_template('recipes/all_recipes.html', recipes = all_recipes)
    return redirect('/')

@app.route('/create_recipe', methods = ['GET', 'POST'])
def create_new_recipe():
    if session.get('user_id'):
        if request.method == 'GET':
            return render_template('recipes/create_recipe.html',data=None,today = date.today())
        if recipe.Recipe.create_recipe(request.form):
            return redirect ('/recipes')
        return render_template('recipes/create_recipe.html',data=request.form)
    return redirect('/')

@app.route('/recipes/<int:id>')
def show_single_recipe(id):
    if session.get('user_id'):
        this_recipe = recipe.Recipe.get_recipe_by_id(id)
        return render_template('recipes/single_recipe.html', recipe=this_recipe)
    return redirect('/')

@app.route('/recipes/<int:id>/edit', methods = ['GET', 'POST'])
def edit_recipe(id):
    if session.get('user_id'):
        if request.method == 'GET':
            this_recipe = recipe.Recipe.get_recipe_by_id(id)
            return render_template('recipes/edit_recipe.html',data=this_recipe)
        recipe.Recipe.update_recipe(request.form, id)
        return redirect(f'/recipes/{id}')
    return redirect('/')

@app.route('/recipes/<int:id>/delete')
def delete_recipe(id):
    recipe.Recipe.delete_recipe(id)
    return redirect('/recipes')