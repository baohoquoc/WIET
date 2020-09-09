# -*- coding: utf-8 -*-
# Author: Rowan
import logging

from firebase_admin import messaging

from project.services import fcm_token_service
from project.services import food_service
from project.services import user_service
from project.services.image_service import update_protocol_for_a_dict


def push_notification(type_of_meal):
    """Push notification

    :param type_of_meal: type of meal
    :return: True if success
    """
    try:
        if type_of_meal not in ['breakfast', 'lunch', 'dinner']:
            return False
        list_all_users = user_service.get_all_uid_service()
        for each_user in list_all_users:
            uid = each_user[0]
            registration_tokens = fcm_token_service.get_fcm_token_by_uid_service(uid)
            if len(registration_tokens) is not 0:
                location = user_service.get_location_by_uid_service(uid)
                if location is None:
                    continue
                en_meal_in = type_of_meal.replace('breakfast', 'morning') \
                    .replace('lunch', 'noon').replace('dinner', 'evening')
                vn_meal_in = type_of_meal.replace('breakfast', 'sáng') \
                    .replace('lunch', 'trưa').replace('dinner', 'tối')
                food = update_protocol_for_a_dict('image',
                                                  food_service.get_notification_meal_service(uid, en_meal_in, location),
                                                  uid)
                if food is None:
                    continue
                message = messaging.MulticastMessage(
                    notification=messaging.Notification(
                        title='Gợi ý món ăn ' + vn_meal_in + ' hôm nay là:',
                        body=food['name'],
                        image=food['image']
                    ),
                    tokens=registration_tokens,
                    data={'id': str(food['id'])}
                )
                response = messaging.send_multicast(message)
                logging.info('-----[push notification] {0} with {1} messages were sent successfully'
                             .format(uid, response.success_count))
        return True
    except Exception as e:
        logging.error(str(e))
        return False


def push_notification_breakfast():
    """Push notification for breakfast

    :return:
    """
    push_notification('breakfast')


def push_notification_lunch():
    """Push notification for lunch

    :return:
    """
    push_notification('lunch')


def push_notification_dinner():
    """Push notification for dinner

    :return:
    """
    push_notification('dinner')
