
from flask import Flask, request, render_template
from dto import coinDTO
from dao import coinDAO
import json


app = Flask(__name__)

@app.route("/")
def intro():
    return render_template("index.html")

@app.route('/select', methods=['POST'])
def select():
    name = request.form.get("name")
    myresult = coinDAO().allcoin(name)

    result_json = json.dumps(myresult)

    return result_json

@app.route('/minmax', methods=['POST'])
def minmax():
    name = request.form.get("name")
    myresult = coinDAO().minmax(name)

    result_json = json.dumps(myresult)

    return result_json

@app.route('/intro', methods=['GET'])
def selectname():
    myresult = coinDAO().selectname()
    result_json = json.dumps(myresult)
    return result_json


if __name__ == "__main__":
    app.run(debug=True)