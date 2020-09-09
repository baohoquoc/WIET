# -*- coding: utf-8 -*-
# Author: Ned
import logging

from project.dao import mealtoday_dao
from project.services import allergy_service
from project.services import food_service
from project.services import user_service
from project.services import recommend_service
from project.utils.allergy_utils import is_allergy_dict
from project.services.image_service import update_protocol_for_a_dict
from project.utils.math_utils import is_numeric


def mealtoday_service(uid, location, temperature):
    """Meal today service

    :param uid: uid
    :param location: location of user
    :param temperature: temperature
    :return: meal object
    """
    try:
        if recommend_service.is_vegan_user_service(uid):
            breakfast = mealtoday_dao.get_vegetarian_food_by_location_mealtoday(location, uid)
            lunch = mealtoday_dao.get_vegetarian_food_by_location_mealtoday(location, uid)
            dinner = mealtoday_dao.get_vegetarian_food_by_location_mealtoday(location, uid)
            weather = mealtoday_dao.get_vegetarian_food_by_location_mealtoday(location, uid)
        else:
            allergies = allergy_service.get_list_allergy_by_uid_service(uid)
            while True:
                breakfast = food_service.get_food_from_super_tag_name_and_location_service('morning', location)
                if not is_allergy_dict(breakfast, allergies):
                    break
            while True:
                lunch = food_service.get_food_from_super_tag_name_and_location_service('noon', location)
                if not is_allergy_dict(lunch, allergies):
                    break
            while True:
                dinner = food_service.get_food_from_super_tag_name_and_location_service('evening', location)
                if not is_allergy_dict(dinner, allergies):
                    break
            while True:
                weather = get_food_by_temp_service(temperature, location)
                if not is_allergy_dict(weather, allergies):
                    break
        if breakfast:
            breakfast['latitude'], breakfast['longitude'] = mealtoday_dao.get_lat_long_from_food_id(
                breakfast['food_id'])
        if lunch:
            lunch['latitude'], lunch['longitude'] = mealtoday_dao.get_lat_long_from_food_id(lunch['food_id'])
        if dinner:
            dinner['latitude'], dinner['longitude'] = mealtoday_dao.get_lat_long_from_food_id(dinner['food_id'])
        if weather:
            weather['latitude'], weather['longitude'] = mealtoday_dao.get_lat_long_from_food_id(weather['food_id'])
        meal = {
            'meal': {
                'breakfast': update_protocol_for_a_dict('image', breakfast, uid),
                'lunch': update_protocol_for_a_dict('image', lunch, uid),
                'dinner': update_protocol_for_a_dict('image', dinner, uid),
                'temperature': update_protocol_for_a_dict('image', weather, uid)
            }
        }
        return meal
    except Exception as e:
        logging.error(str(e))
        return {
            'meal': {
                'breakfast': None,
                'lunch': None,
                'dinner': None,
                'temperature': None
            }
        }


def check_temperature_service(temperature):
    """Validate type data of temperature

    :param temperature: temperature
    :return: True or False
    """
    return is_numeric(temperature)


def get_location_for_mealtoday_by_uid_service(uid):
    """Get location by uid

    :param uid: uid
    :return: location if exist, None if user or location not exist
    """
    return user_service.get_location_by_uid_service(uid)


def get_food_by_temp_service(temperature, location):
    """Check temperature

    :param location: location
    :param temperature: temperature
    :return: hot or mild or cold
    """
    min_normal_temp = 20
    max_normal_temp = 27
    try:
        if float(temperature) > max_normal_temp:
            food = food_service.get_food_from_super_tag_name_and_location_service('hot', location)
        elif float(temperature) < min_normal_temp:
            food = food_service.get_food_from_super_tag_name_and_location_service('cold', location)
        else:
            food = food_service.get_food_from_super_tag_name_and_location_service('normal', location)
        return food
    except Exception as e:
        logging.error(str(e))
        return None


def get_lat_long_from_food_id(food_id):
    """Get latitude and longitude from food id

    :param food_id:
    :return: latitude and longitude
    """
    return mealtoday_dao.get_lat_long_from_food_id(food_id)


def get_vegetarian_food_by_location_mealtoday(location, uid):
    """Get vegetarian food by location

    :param location: location
    :param uid: uid
    :return: food
    """
    return mealtoday_dao.get_vegetarian_food_by_location_mealtoday(location, uid)

