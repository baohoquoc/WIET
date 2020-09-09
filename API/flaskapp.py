# -*- coding: utf-8 -*-	c
# Author: Rowan

from project import app

if __name__ == '__main__':
    # delete this config when deploy
    app.run(debug=app.config['DEBUG'], port=app.config['PORT'], use_reloader=False)
