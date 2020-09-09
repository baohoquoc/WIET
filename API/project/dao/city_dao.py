# -*- coding: utf-8 -*-
# Author: Rowan
from project.models.City import City
from project.utils.object_utils import list_object_to_dict_utils


def get_all_city_dao(is_dict_result=False):
    """Get all cities in database

    :param is_dict_result: True return dict
    :return: All cities in database
    """
    cities = City.query.order_by(City.name).all()
    if is_dict_result:
        return list_object_to_dict_utils(cities)
    return cities


def search_city_dao(keyword, is_dict_result=False):
    """Search city in database

    :param keyword: keyword from clients
    :param is_dict_result: True return dict
    :return: All searched cities in database
    """
    keyword = '%{}%'.format(keyword.replace('%', '/%').replace('_', '/_'))
    searched_cities = City.query.order_by(City.name).filter(City.name.ilike(keyword) |
                                                            City.name_with_type.ilike(keyword) |
                                                            City.slug.ilike(keyword) |
                                                            City.type.ilike(keyword)).all()
    if is_dict_result:
        return list_object_to_dict_utils(searched_cities)
    return searched_cities


# Author: Ned
from project.utils.object_utils import object_to_dict_utils


def get_city_by_name(name, is_dict_result=False):
    """Get location by name

    :param name: name of city
    :param is_dict_result: True return dict, False return object
    :return: city
    """
    city = City.query.filter_by(name=name).first()
    if city:
        if is_dict_result:
            return object_to_dict_utils(city)
        return city
    else:
        return None
