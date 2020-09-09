# -*- coding: utf-8 -*-
# Author: Rowan
from flask import render_template

from project import app


@app.route('/api/v1.0/docs', methods=['GET'])
def docs():
    return render_template('/redoc/redoc.html')
