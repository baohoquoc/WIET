# -*- coding: utf-8 -*-
# Author: Rowan
from datetime import datetime


def get_allergy_schema():
    """Allergy schema

    :return: schema
    """
    return {
        'meta_tag_ids': {
            'type': 'list',
            'minlength': 1,
            'schema': {
                'type': 'integer'
            }
        }
    }


def get_bookmark_schema():
    """Bookmark schema

    :return: schema
    """
    return {
        'food_id': {
            'type': 'integer'
        }
    }


def get_rating_schema():
    """Rating schema

    :return: schema
    """
    return {
        'rating': {
            'type': 'list',
            'minlength': 1,
            'schema': {
                'type': 'dict',
                'schema': {
                    'meta_tag_id': {
                        'type': 'integer'
                    },
                    'tag_ids': {
                        'type': 'list',
                        'minlength': 1,
                        'schema': {
                            'type': 'integer'
                        }
                    }
                }
            }
        }
    }


def get_auth_schema():
    """Auth schema

    :return: schema
    """
    return {
        'firebase_token': {
            'type': 'string',
            'empty': False
        },
        'fcm_token': {
            'type': 'string',
            'empty': False
        }
    }


def get_user_info_schema():
    """User info shema

    :return: schema
    """
    return {
        'uid': {
            'type': 'string',
            'empty': False
        },
        'email': {
            'type': 'string',
            'regex': '^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$',
            'empty': False
        },
        'dob': {
            'type': 'date',
            'coerce': lambda s: datetime.strptime(s, '%Y-%m-%d'),
            'empty': False
        },
        'fullname': {
            'type': 'string',
            'empty': False
        },
        'is_vegetarian': {
            'type': 'boolean',
            'empty': False
        },
        'avatar': {
            'type': 'string',
            'empty': False
        },
        'location': {
            'type': 'string',
            'empty': True
        }
    }


def get_location_schema():
    """Location schema

    :return: schema
    """
    return {
        'location': {
            'type': 'string'
        }
    }


def get_history_schema():
    """History schema

    :return: schema
    """
    return {
        'food_id': {
            'type': 'integer'
        }
    }
