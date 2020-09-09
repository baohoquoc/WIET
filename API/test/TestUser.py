# -*- coding: utf-8 -*-
# Author: Rowan
import json
import unittest

from project import app
from test import AUTH_HEADERS, UNAUTH_HEADERS, DEFAULT_PATH


class TestUser(unittest.TestCase):
    def testAuth1(self):
        with app.test_client() as client:
            data = {
                "firebase_token": "eyJhbGciOiJSUzI1NiIsImtpZCI6ImRjMGMzNWZlYjBjODIzYjQyNzdkZDBhYjIwNDQzMDY5ZGYzMGZkZWEi"
                                  "LCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiTmlnaHQgRm94IiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZ"
                                  "XVzZXJjb250ZW50LmNvbS9hLS9BT2gxNEdodnZTRm5sdF84WG9pVzROZkd1UllLUUZHTjJjX3pia2hLV3l2R"
                                  "z1zOTYtYyIsImlzcyI6Imh0dHBzOi8vc2VjdXJldG9rZW4uZ29vZ2xlLmNvbS9iYzEzLXdpZXQiLCJhdWQiO"
                                  "iJiYzEzLXdpZXQiLCJhdXRoX3RpbWUiOjE1ODYyNTIzNTAsInVzZXJfaWQiOiJCRThtNzBwaGRFZVJ0dmpOM"
                                  "zhiTkdFRGZoSFYyIiwic3ViIjoiQkU4bTcwcGhkRWVSdHZqTjM4Yk5HRURmaEhWMiIsImlhdCI6MTU4NjI1M"
                                  "jM1MCwiZXhwIjoxNTg2MjU1OTUwLCJlbWFpbCI6Imh1eWVubmd1eWV0dmllbWxvbmdAZ21haWwuY29tIiwiZ"
                                  "W1haWxfdmVyaWZpZWQiOnRydWUsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZ29vZ2xlLmNvbSI6WyIxM"
                                  "TYyOTA5MTA4NDYwNzMxMTgxODIiXSwiZW1haWwiOlsiaHV5ZW5uZ3V5ZXR2aWVtbG9uZ0BnbWFpbC5jb20iX"
                                  "X0sInNpZ25faW5fcHJvdmlkZXIiOiJnb29nbGUuY29tIn19.J0kps2eVOmbfEUpcq2XXoAXa32TyptCYM9AM"
                                  "IpuDwbfsFmu-fy2PqAgMxrLt5yx9sbr5JG4-SC9VUFfSLCAhAwTmjWemo2XUrW8IW8jf4AvxLi-LYtG29Fwc"
                                  "6sSEio9TRi0jP2B38awjEV7paRagoukil0nmmk-Udwl7_IH0jprfdnbWm8UqfP9NPRtAd1JaY_RJkC-iSEtQ"
                                  "MxIRSB4aihaj4KH5iWeDjvZeKKILTaIAhpwb1DyYPalQrlv20XW5u9eLcRNhSVcu6l5bI38eDpR6QVbVmZDP"
                                  "b0ixB4v5v4CwBLVYr-z26oqcb68P8VN8bTX5S6fTxP2MR6h9iOh5Rg",
                "fcm_token": "fnvb2hFE5bc:APA91bEVRufkbaqRFY9Xz9xIT2AqUR2OkyO8dCl9O8vj5ZtZITFJIFySunT4kpan3pG_zQE3IcQV3"
                             "YfCTJa8fVoAsq9NIQU5z_-t9BHgvzDbIfDstbhX6a2T-fzgILhVKyvuJTHBGVXt"
            }
            res = client.post(DEFAULT_PATH.format('/auth'), data=json.dumps(data), headers=UNAUTH_HEADERS)
            self.assertEqual(402, res.status_code)

    def testAuth2(self):
        with app.test_client() as client:
            data = {
                "firebase_token": "eyJhbGciOiJSUzI1NiIsImtpZCI6ImRjMGMzNWZlYjBjODIzYjQyNzdkZDBhYjIwNDQzMDY5ZGYzMGZkZWEi"
                                  "LCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiTmlnaHQgRm94IiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZ"
                                  "XVzZXJjb250ZW50LmNvbS9hLS9BT2gxNEdodnZTRm5sdF84WG9pVzROZkd1UllLUUZHTjJjX3pia2hLV3l2R"
                                  "z1zOTYtYyIsImlzcyI6Imh0dHBzOi8vc2VjdXJldG9rZW4uZ29vZ2xlLmNvbS9iYzEzLXdpZXQiLCJhdWQiO"
                                  "iJiYzEzLXdpZXQiLCJhdXRoX3RpbWUiOjE1ODYyNTIzNTAsInVzZXJfaWQiOiJCRThtNzBwaGRFZVJ0dmpOM"
                                  "zhiTkdFRGZoSFYyIiwic3ViIjoiQkU4bTcwcGhkRWVSdHZqTjM4Yk5HRURmaEhWMiIsImlhdCI6MTU4NjI1M"
                                  "jM1MCwiZXhwIjoxNTg2MjU1OTUwLCJlbWFpbCI6Imh1eWVubmd1eWV0dmllbWxvbmdAZ21haWwuY29tIiwiZ"
                                  "W1haWxfdmVyaWZpZWQiOnRydWUsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZ29vZ2xlLmNvbSI6WyIxM"
                                  "TYyOTA5MTA4NDYwNzMxMTgxODIiXSwiZW1haWwiOlsiaHV5ZW5uZ3V5ZXR2aWVtbG9uZ0BnbWFpbC5jb20iX"
                                  "X0sInNpZ25faW5fcHJvdmlkZXIiOiJnb29nbGUuY29tIn19.J0kps2eVOmbfEUpcq2XXoAXa32TyptCYM9AM"
                                  "IpuDwbfsFmu-fy2PqAgMxrLt5yx9sbr5JG4-SC9VUFfSLCAhAwTmjWemo2XUrW8IW8jf4AvxLi-LYtG29Fwc"
                                  "6sSEio9TRi0jP2B38awjEV7paRagoukil0nmmk-Udwl7_IH0jprfdnbWm8UqfP9NPRtAd1JaY_RJkC-iSEtQ"
                                  "MxIRSB4aihaj4KH5iWeDjvZeKKILTaIAhpwb1DyYPalQrlv20XW5u9eLcRNhSVcu6l5bI38eDpR6QVbVmZDP"
                                  "b0ixB4v5v4CwBLVYr-z26oqcb68P8VN8bTX5S6fTxP2MR6h9iOh5Rg",
                "fcm_token2": "fnvb2hFE5bc:APA91bEVRufkbaqRFY9Xz9xIT2AqUR2OkyO8dCl9O8vj5ZtZITFJIFySunT4kpan3pG_zQE3IcQV"
                              "3YfCTJa8fVoAsq9NIQU5z_-t9BHgvzDbIfDstbhX6a2T-fzgILhVKyvuJTHBGVXt"
            }
            res = client.post(DEFAULT_PATH.format('/auth'), data=json.dumps(data), headers=UNAUTH_HEADERS)
            self.assertEqual(401, res.status_code)

    def testUpdateUser1(self):
        with app.test_client() as client:
            data = {
                "uid": "BE8m70phdEeRtvjN38bNGEDfhHV2",
                "email": "huyennguyetviemlong@gmail.com",
                "dob": "1998-10-18",
                "fullname": "Hiếu Lương",
                "is_vegetarian": False,
                "avatar": "https://graph.facebook.com/1332042503669281/picture"
            }
            res = client.put(DEFAULT_PATH.format('/user'), data=json.dumps(data), headers=AUTH_HEADERS)
            self.assertEqual(201, res.status_code)

    def testUpdateUser2(self):
        with app.test_client() as client:
            data = {
                "uid": "BE8m70phdEeRtvjN38bNGEDfhHV2",
                "email": "huyennguyetviemlong@gmail.com",
                "dob": "1998-10-18",
                "fullname": "Hiếu Lương",
                "is_vegetarian": False,
                "avatar2": "https://graph.facebook.com/1332042503669281/picture"
            }
            res = client.put(DEFAULT_PATH.format('/user'), data=json.dumps(data), headers=AUTH_HEADERS)
            self.assertEqual(401, res.status_code)

    def testUpdateUser3(self):
        with app.test_client() as client:
            data = {
                "uid": "111111BE8m70phdEeRtvjN38bNGEDfhHV2",
                "email": "huyennguyetviemlong@gmail.com",
                "dob": "1998-10-18",
                "fullname": "Hiếu Lương",
                "is_vegetarian": False,
                "avatar": "https://graph.facebook.com/1332042503669281/picture"
            }
            res = client.put(DEFAULT_PATH.format('/user'), data=json.dumps(data), headers=AUTH_HEADERS)
            self.assertEqual(402, res.status_code)

    def testUpdateLocation1(self):
        with app.test_client() as client:
            data = {
                "location": "Đà Nẵng"
            }
            res = client.patch(DEFAULT_PATH.format('/save_last_location'), data=json.dumps(data), headers=AUTH_HEADERS)
            self.assertEqual(201, res.status_code)

    def testUpdateLocation2(self):
        with app.test_client() as client:
            data = {
                "location2": "Đà Nẵng"
            }
            res = client.patch(DEFAULT_PATH.format('/save_last_location'), data=json.dumps(data), headers=AUTH_HEADERS)
            self.assertEqual(401, res.status_code)
