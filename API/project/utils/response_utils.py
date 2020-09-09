# -*- coding: utf-8 -*-
# Author: Rowan
from flask import jsonify


def make_response_utils(message='', status=200, value=None, values=None, error='', success=True):
    """Jsonify a JSON response

    :param message: Success message
    :param status: HTTP status
    :param value: Success value object
    :param values: Success value list
    :param error: Error message
    :param success: Is success
    :return: JSON response
    """
    if values is None:
        values = []
    if value is None:
        value = {}
    return jsonify({
        'message': message,
        'status': status,
        'value': value,
        'values': values,
        'error': error,
        'success': success
    })
