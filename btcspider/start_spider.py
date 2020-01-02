import os
import time
while True:
	#time.sleep(3)
	try:
		os.system("scrapy crawl btcspider")
	except:
		os.exit()