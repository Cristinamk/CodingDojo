from re import S
from flask import Flask, render_template, request, redirect, session
app = Flask(__name__)
app.secret_key = 'Secret'

@app.route('/')
def index():
    if "count" not in session:
        session["count"] = 0
    session['count'] += 1
    return render_template('index.html', count=session['count'])

@app.route('/increment', methods=['POST'])
def increment_by_two():
    session['count'] += 1
    #We only increment by 1 since reloading the page also increments
    return redirect('/')

@app.route('/destroy_session', methods=['POST'])
def clear():
    session['count'] = 0
    return redirect('/')

if __name__ == "__main__":
    app.run(debug=True)
