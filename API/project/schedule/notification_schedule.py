# -*- coding: utf-8 -*-
# Author: Rowan
from project import scheduler
from project.services import notification_service

TRIGGER_TYPE = 'cron'
BREAKFAST_TIME = '09:21:10'
LUNCH_TIME = '11:00:00'
DINNER_TIME = '17:00:00'

scheduler.add_job(func=notification_service.push_notification_breakfast,
                  trigger=TRIGGER_TYPE,
                  hour=int(BREAKFAST_TIME[0:2]),
                  minute=int(BREAKFAST_TIME[3:5]),
                  second=int(BREAKFAST_TIME[6:8]))

scheduler.add_job(func=notification_service.push_notification_lunch,
                  trigger=TRIGGER_TYPE,
                  hour=int(LUNCH_TIME[0:2]),
                  minute=int(LUNCH_TIME[3:5]),
                  second=int(LUNCH_TIME[6:8]))

scheduler.add_job(func=notification_service.push_notification_dinner,
                  trigger=TRIGGER_TYPE,
                  hour=int(DINNER_TIME[0:2]),
                  minute=int(DINNER_TIME[3:5]),
                  second=int(DINNER_TIME[6:8]))
