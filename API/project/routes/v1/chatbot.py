# -*- coding: utf-8 -*-
# Author: Rowan, Ned
from flask import request, make_response, jsonify
from project.dao import food_dao
from project import app
import re


@app.route('/webhook', methods=['GET', 'POST'])
def webhook():
    req = request.get_json(force=True)
    print(req)
    action = req.get('queryResult').get('action')
    if action == 'IConsult.IConsult-food_in_location.IConsult-food_in_location-input_location':
        location = req.get('queryResult').get('parameters').get('geo-city')
        food_stores = food_dao.get_food_store_by_location_webhook(location, 5)
        if food_stores is not None:
            res = food_stores
            print(food_stores)
            return make_response(jsonify({'fulfillmentText': 'No food',
                                          "fulfillmentMessages": [
                                              {
                                                  "card": {
                                                      "title": item['name'] + ' - ' + str(item['price']) + ' VND',
                                                      "subtitle": item['store_name'] + ' - ' + item['address'],
                                                      "imageUri": "https://cdn.iconscout.com/icon/free/png-512/fast-food-1851561-1569286.png",
                                                      "buttons": [
                                                          {
                                                              "text": "Open in now.vn",
                                                              "postback": create_url(item)
                                                          }
                                                      ]
                                                  }
                                              } for item in res]
                                          }))


def create_url(food_store_object):
    now_url = "https://www.now.vn/"
    split_address = food_store_object['address'].split(", ")
    city = split_address[-1]
    city.strip()
    city = sub_and_replace(city)
    store_name = food_store_object['store_name']
    store_name.strip()
    store_name = sub_and_replace(store_name)
    store_name = store_name.replace("-&-", "-")
    store_name = store_name.replace("---", "-")
    url = now_url + city + '/' + store_name
    return url


def sub_and_replace(s):
    s = s.lower()
    s = re.sub(r'[àáạảãâầấậẩẫăằắặẳẵ]', 'a', s)
    s = re.sub(r'[ÀÁẠẢÃĂẰẮẶẲẴÂẦẤẬẨẪ]', 'A', s)
    s = re.sub(r'[èéẹẻẽêềếệểễ]', 'e', s)
    s = re.sub(r'[ÈÉẸẺẼÊỀẾỆỂỄ]', 'E', s)
    s = re.sub(r'[òóọỏõôồốộổỗơờớợởỡ]', 'o', s)
    s = re.sub(r'[ÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠ]', 'O', s)
    s = re.sub(r'[ìíịỉĩ]', 'i', s)
    s = re.sub(r'[ÌÍỊỈĨ]', 'I', s)
    s = re.sub(r'[ùúụủũưừứựửữ]', 'u', s)
    s = re.sub(r'[ƯỪỨỰỬỮÙÚỤỦŨ]', 'U', s)
    s = re.sub(r'[ỳýỵỷỹ]', 'y', s)
    s = re.sub(r'[ỲÝỴỶỸ]', 'Y', s)
    s = re.sub(r'[Đ]', 'D', s)
    s = re.sub(r'[đ]', 'd', s)
    s = s.replace(" ", "-")
    return s
