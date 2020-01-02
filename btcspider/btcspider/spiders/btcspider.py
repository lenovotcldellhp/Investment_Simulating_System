# -*- coding: utf-8 -*-
import scrapy
import sys
from scrapy.http import Request
import MySQLdb
import time

reload(sys)
sys.setdefaultencoding('utf-8')

db = MySQLdb.connect(host="39.105.45.13",port=3306,user="root",passwd="guigeyangeburangjige",db="finance")
db.set_character_set('utf8')  # 务必做这个设置，否则默认latin1
# 使用cursor()方法获取操作游标
cursor = db.cursor()

class DB_Action:
	@staticmethod
	def save_btc(price):
		sql_save_btc = "insert into btc_price(price) values(" + price + ");"
		sql_update_btc_newest="update BTC_LTC_NEWEST_PRICE set PRICE ="+price+" where TYPE='BTC';"
		#print sql_save_btc
		try:
			cursor.execute(sql_save_btc)
			cursor.execute(sql_update_btc_newest)
			db.commit()
		except Exception,e:
			print "存储比特币数据出现问题"
			print e
	@staticmethod
	def save_ltc(price):
		sql_save_ltc = "insert into ltc_price(price) values(" + price + ");"
		# print sql_save_btc
		sql_update_ltc_newest="update BTC_LTC_NEWEST_PRICE set PRICE ="+price+" where TYPE='LTC';"
		try:
			cursor.execute(sql_save_ltc)
			cursor.execute(sql_update_ltc_newest)
			db.commit()
		except Exception, e:
			print "存储莱特币数据出现问题"
			print e
class btcspiderSpider(scrapy.Spider):
	name = 'btcspider'
	allowed_domains = ['www.btctrade.com']
	start_urls = [
		'https://www.btctrade.com/btc/'
	]

	def start_requests(self):#重写start_requests函数，用来指定怎样加载要爬的URL

		urls=[
			'https://www.btctrade.com/btc/',
			'https://www.btctrade.com/ltc/'
		]
		#print "***********"
		#print DB_Action.load_stock()
	#	for stockcode in DB_Action.load_stock():
	#		urls.append("https://gupiao.baidu.com/stock/"+stockcode[0]+".html")

	#	print urls


		for url in urls:
			print "正在爬取"+url
			time.sleep(1)
			yield scrapy.Request(url)


	def parse(self, response):#对爬到的response进行处理
		#print "=================="
		print response
		print "******************"
		title=response.xpath("//title/text()").extract()[0].decode("utf-8")
		print title
		price_str = response.xpath('/html/body/div/div/div/div/div/div[3]/div[1]/div/div[1]/strong/text()').extract()[0]
		print price_str

			#print price_str#从网页上得到的价格原数字，形如"$3393.21"
		#接下来对价格字符串进行处理，去除$这个字符，得到纯数字的字符串
		newstr = ""
		length = len(price_str)
		# print length
		i = 0
		j = 0
		while i < len(price_str):
			if price_str[i] == "$":
				i = i + 1
			else:
				try:
					newstr = newstr + price_str[i]
					i = i + 1
				except:
					pass
				# print str[i]
		if "LTC" in title:
			print "莱特币价格"
			#调用比特币价格存储函数
			DB_Action.save_ltc(newstr)
		else:
			print "比特币价格"
			DB_Action.save_btc(newstr)
			#调动莱特币价格存储函数
		print newstr


