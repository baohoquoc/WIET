# -*- coding: utf-8 -*-
# Author: Rowan
import unittest

from project import app
from test import AUTH_HEADERS, DEFAULT_PATH


class TestFoods(unittest.TestCase):
    def testGetFoodById1(self):
        with app.test_client() as client:
            res = client.get(DEFAULT_PATH.format('/foods/1'), headers=AUTH_HEADERS)
            self.assertEqual(201, res.status_code)

    def testGetFoodById2(self):
        with app.test_client() as client:
            res = client.get(DEFAULT_PATH.format('/foods/0'), headers=AUTH_HEADERS)
            self.assertEqual(401, res.status_code)

    def testSearchLimitedFoods(self):
        with app.test_client() as client:
            res = client.get(DEFAULT_PATH.format('/foods'), headers=AUTH_HEADERS)
            self.assertEqual(201, res.status_code)

    def testGetMealsToday1(self):
        with app.test_client() as client:
            res = client.get(DEFAULT_PATH.format('/mealtoday?location=Đà Nẵng&temperature=27'), headers=AUTH_HEADERS)
            self.assertEqual(200, res.status_code)

    def testGetMealsToday2(self):
        with app.test_client() as client:
            res = client.get(DEFAULT_PATH.format('/mealtoday?location=1&temperature=27'), headers=AUTH_HEADERS)
            self.assertEqual(400, res.status_code)

    def testGetMealsToday3(self):
        with app.test_client() as client:
            res = client.get(DEFAULT_PATH.format('/mealtoday?location=Đà Nẵng'), headers=AUTH_HEADERS)
            self.assertEqual(401, res.status_code)
