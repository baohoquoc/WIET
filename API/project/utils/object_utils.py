# -*- coding: utf-8 -*-
# Author: Rowan


def object_to_dict_utils(obj):
    """Convert object to a dictionary

    :param obj: Object
    :return: Dict
    """
    _dict = obj.__dict__
    del _dict['_sa_instance_state']
    return _dict


def list_object_to_dict_utils(objs):
    """Convert list of object to a list of dictionary

    :param objs: list of object
    :return: list of dict
    """
    result = []
    for obj in objs:
        result.append(object_to_dict_utils(obj))
    return result
