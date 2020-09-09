# -*- coding: utf-8 -*-
# Author: Rowan


def is_allergy(food, allergies):
    """Is allergy

    :param allergies: list of allergies
    :param food: food
    :return: True if tag contain in allergies
    """
    if food:
        for allergy in allergies:
            if allergy.lower() in food.name.lower():
                return True
        return False
    else:
        return False


def is_allergy_dict(food, allergies):
    """Is allergy

    :param allergies: list of allergies
    :param food: food
    :return: True if tag contain in allergies
    """
    if food:
        for allergy in allergies:
            if allergy.lower() in food['name'].lower():
                return True
        return False
    else:
        return False
