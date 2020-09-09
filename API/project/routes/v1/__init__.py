# -*- coding: utf-8 -*-
# Author: Rowan
import glob
import os

from flask import send_from_directory, render_template

from project import app
from project.utils.response_utils import make_response_utils


def get_message():
    return 'Please read the API documentation at {0}/docs'.format(app.config['API_ENDPOINT'])


@app.route('/', methods=['GET'])
def start():
    return render_template('index.html')


@app.route('/<path:other>', methods=['GET'])
def message(other):
    return make_response_utils(message=get_message()), 200


@app.route('/favicon.ico')
def favicon():
    return send_from_directory(os.path.join(app.root_path, 'static'),
                               'favicon.ico', mimetype='image/vnd.microsoft.icon')


# load all modules
__all__ = [os.path.basename(f)[:-3] for f in glob.glob(os.path.dirname(__file__) + "/*.py")]
