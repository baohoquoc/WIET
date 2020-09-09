# -*- coding: utf-8 -*-
# Author: Rowan
ACCESS_TOKEN = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1ODYyNTI0ODksIm5iZiI6MTU4NjI1MjQ4OSwianRpIjoiYzgzMGI' \
               'yN2QtYTMxYy00NDgzLTkzYzMtYjkzNjlmZmU3NjM0IiwiaWRlbnRpdHkiOiJCRThtNzBwaGRFZVJ0dmpOMzhiTkdFRGZoSFYyIiw' \
               'iZnJlc2giOmZhbHNlLCJ0eXBlIjoiYWNjZXNzIn0.wbDFEUPcGKsVh6dZpOUUxOoFQYjgVipOh9Yi0GsiBBQ'
AUTH_HEADERS = {
    'Authorization': 'Bearer ' + ACCESS_TOKEN,
    'Content-Type': 'application/json'
}
UNAUTH_HEADERS = {
    'Content-Type': 'application/json'
}
DEFAULT_PATH = '/api/v1.0{}'
