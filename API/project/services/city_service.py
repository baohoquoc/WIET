# -*- coding: utf-8 -*-
# Author: Rowan
from project.dao import city_dao


def get_all_city_tag_service():
    """Get all cities service

    :return: Cities
    """
    return city_dao.get_all_city_dao(is_dict_result=True)


def search_city_service(keyword):
    """Get all searched cities service

    :param keyword: keyword from clients
    :return: Searched cities
    """
    if keyword is None:
        keyword = ''
    return city_dao.search_city_dao(keyword, is_dict_result=True)


def get_city_by_name_service(name):
    """Get location by name

    :param name: name of city
    :return: city
    """
    return city_dao.get_city_by_name(name, is_dict_result=True)
