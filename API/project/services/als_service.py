# # -*- coding: utf-8 -*-
# # Author: Rowan, Ned
# import pandas as pd
# from pyspark.ml.feature import StringIndexer
# from pyspark.ml.recommendation import ALS
# from pyspark.sql import SparkSession
# MAX_ITER = 5
# REG_PARAM = 0.01
# NUM_OF_RECOMMENDED_FOOD = 300
# from project import celery, app
# from project import db
# spark = SparkSession.builder.appName('rec').getOrCreate()
#
#
# @celery.task()
# def als_recommend_service():
#     """ALS recommend service
#
#     :return: void, push data to recommend table
#     """
#     df = pd.read_sql_table('rating', app.config['SQLALCHEMY_DATABASE_URI'], columns=['user_id', 'food_id', 'rate'])
#     df = spark.createDataFrame(df)
#     string_indexer = StringIndexer(inputCol="user_id", outputCol="user_id_int")
#     to_int_model = string_indexer.fit(df)
#     indexed = to_int_model.transform(df)
#     # Smaller dataset so we will use 0.8 / 0.2
#     (training, test) = indexed.randomSplit([0.8, 0.2])
#     # Build the recommendation model using ALS on the training data
#     als = ALS(maxIter=MAX_ITER, regParam=REG_PARAM, userCol="user_id_int", itemCol="food_id", ratingCol="rate",
#               coldStartStrategy="drop", nonnegative=True)
#     model = als.fit(training)
#     # Evaluate the model by computing the RMSE on the test data
#     # predictions = model.transform(test)
#     user_recs = model.recommendForAllUsers(NUM_OF_RECOMMENDED_FOOD)
#     temp = indexed.groupBy(indexed['user_id'])
#     df = temp.max().withColumnRenamed('max(user_id_int)', 'user_id_int')
#     table = user_recs.join(df, on=["user_id_int"], how="left")
#     table = table.drop('max(food_id)', 'max(rate)', 'user_id_int')
#     for row in table.collect():
#         for food in row.recommendations:
#             db.session.query(db.func.upsert_als_when_conflict(row.user_id, food.food_id, food.rating)).one()
#     db.session.commit()
