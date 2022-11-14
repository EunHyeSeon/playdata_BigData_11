import pandas as pd
import numpy as np
import pymysql

crypto_df = pd.read_csv('crypto-markets.csv')
crypto_df.head()



assetsCode = ['BTC','ETH','XRP','LTC']

# coverting open, close, high and low price of crypto currencies into GBP values since current price is in Dollars
# if currency belong to this list ['BTC','ETH','XRP','LTC']
crypto_df['open'] = crypto_df[['open', 'asset']].apply(lambda x: (float(x[0]) * 0.75) if x[1] in assetsCode else np.nan, axis=1)
crypto_df['close'] = crypto_df[['close', 'asset']].apply(lambda x: (float(x[0]) * 0.75) if x[1] in assetsCode else np.nan, axis=1)
crypto_df['high'] = crypto_df[['high', 'asset']].apply(lambda x: (float(x[0]) * 0.75) if x[1] in assetsCode else np.nan, axis=1)
crypto_df['low'] = crypto_df[['low', 'asset']].apply(lambda x: (float(x[0]) * 0.75) if x[1] in assetsCode else np.nan, axis=1)

# dropping rows with null values by asset column
crypto_df.dropna(inplace=True)

# reset the data frame index
crypto_df.reset_index(drop=True ,inplace=True)

# dropping irrelevant columns
crypto_df.drop(labels=['slug', 'ranknow', 'volume', 'market', 'close_ratio', 'spread'], inplace=True, axis=1)

# date column formatting
crypto_df['date']=pd.to_datetime(crypto_df['date'].astype(str), format='%d-%m-%Y' , errors='raise')

# df 소수점 맞추기
crypto_df.round(3)

#to list
crypto_list = crypto_df.values.tolist()

# 테이블 삭제용
connection = pymysql.connect(host = '127.0.0.1',
                            port = 3306,
                            user = 'bigdata',
                            password = 'bigdata',
                            db = 'playdata')
cursor = connection.cursor()
try:
    cursor.execute('DROP TABLE IF EXISTS Crypto')
    print('Table dropped')
except Exception as e:
    raise(e)
finally:
    cursor.close()
    connection.close()

# table 생성
connection = pymysql.connect(host = '127.0.0.1',
                            port = 3306,
                            user = 'bigdata',
                            password = 'bigdata',
                            db = 'playdata')
sql = """
create table Crypto(
    id int auto_increment primary key,
    asset varchar(30) not null,
    name varchar(30) not null,
    date date,
    open float default 0,
    high float default 0,
    low float default 0,
    close float default 0
)
"""
cursor = connection.cursor()
try:
    cursor.execute(sql)
    print("Table Created")
except Exception as e:
    print("Fail")
    raise(e)
finally:
    cursor.close()
    connection.close()

# insert
try:
    connection = pymysql.connect(host='127.0.0.1', port=3306, user='bigdata', password='bigdata', db='playdata', charset='utf8')
    cursor = connection.cursor()
    cursor.executemany("INSERT INTO Crypto(asset, name, date, open, high, low, close) VALUES (%s,%s,%s,%s,%s,%s,%s)", crypto_list)
    connection.commit()
    print('Data Inserted Successfully')
except Exception as e:
    print(str(e))
    print('Data Insertion Failed')
finally:
    # finally block will help with always closing the connection to DB even in case of error.
    cursor.close()
    connection.close()

# alter table crypto modify column date varchar(20);
try:
    connection = pymysql.connect(host='127.0.0.1', port=3306, user='bigdata', password='bigdata', db='playdata', charset='utf8')
    cursor = connection.cursor()
    cursor.execute("alter table crypto modify column date varchar(20)")
    connection.commit()
    print('Data Altered Successfully')
except Exception as e:
    print(str(e))
    print('Data Altered Failed')
finally:
    # finally block will help with always closing the connection to DB even in case of error.
    cursor.close()
    connection.close()

