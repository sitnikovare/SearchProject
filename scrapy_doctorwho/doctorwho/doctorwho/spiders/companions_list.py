import scrapy


class CompanionsListSpider(scrapy.Spider):
    name = 'companions_list'
    allowed_domains = ['tardis.fandom.com']
    start_urls = ['http://tardis.fandom.com/ru/wiki/Спутники_Доктора']

    custom_settings={ 'FEED_URI': "results/%(name)s_%(time)s.csv",
                      'FEED_FORMAT': 'csv'}

    def parse(self, response):
        comp_name = response.xpath("//div[@id='gallery-0']//div[@class='lightbox-caption']//a/text()").getall()
        comp_link = response.xpath("//div[@id='gallery-0']//div[@class='lightbox-caption']//a/@href").getall()

        row_data = zip(comp_name, comp_link)

        for item in row_data:
            scrapped_info = {
                'name': item[0],
                'link': item[1],
            }
            yield scrapped_info
