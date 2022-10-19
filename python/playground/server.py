from turtle import color
from flask import Flask, render_template
app = Flask(__name__)
@app.route('/play')
def play_one():
    return render_template("index.html",num=3,color="blue")

@app.route('/play/<int:num>')
def play_two(num):
    return render_template("index.html",color="blue" ,num=num)

@app.route('/play/<int:num>/<color>')
def play_three(num,color):
    return render_template("index.html",num=num,color=color )

if __name__=="__main__":
    app.run(debug=True)
