# -*- coding: utf-8 -*-
# Author: Rowan
import json
import unittest

from project import app
from test import AUTH_HEADERS, DEFAULT_PATH


class TestSurvey(unittest.TestCase):
    def testGetSurvey(self):
        with app.test_client() as client:
            res = client.get(DEFAULT_PATH.format('/survey'), headers=AUTH_HEADERS)
            self.assertEqual(201, res.status_code)

    def testSearchTagsForSurvey(self):
        with app.test_client() as client:
            res = client.get(DEFAULT_PATH.format('/survey/search'), headers=AUTH_HEADERS)
            self.assertEqual(201, res.status_code)

    def testRatingSurvey1(self):
        with app.test_client() as client:
            data = {
                "rating": [
                    {
                        "meta_tag_id": 1,
                        "tag_ids": [
                            4221,
                            4503
                        ]
                    },
                    {
                        "meta_tag_id": 2,
                        "tag_ids": [
                            4222,
                            4224
                        ]
                    }
                ]
            }
            res = client.post(DEFAULT_PATH.format('/rating'), data=json.dumps(data), headers=AUTH_HEADERS)
            self.assertEqual(201, res.status_code)

    def testRatingSurvey2(self):
        with app.test_client() as client:
            data = {
                "rating": [
                    {
                        "meta_tag_id": 1,
                        "tag_ids": [
                            1,
                            2
                        ]
                    }
                ]
            }
            res = client.post(DEFAULT_PATH.format('/rating'), data=json.dumps(data), headers=AUTH_HEADERS)
            self.assertEqual(402, res.status_code)

    def testGetTopRatingFoods(self):
        with app.test_client() as client:
            res = client.get(DEFAULT_PATH.format('/rating/top'), headers=AUTH_HEADERS)
            self.assertEqual(201, res.status_code)
