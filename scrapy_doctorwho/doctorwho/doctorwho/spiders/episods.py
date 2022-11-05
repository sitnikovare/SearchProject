import scrapy


class EpisodsSpider(scrapy.Spider):
    name = 'episods'
    allowed_domains = ['tardis.fandom.com']
    start_urls = [
                #   'http://tardis.fandom.com/ru/wiki/Пилотный_эпизод',
                #   'http://tardis.fandom.com/ru/wiki/Создатели_мифов',
                #   'http://tardis.fandom.com/ru/wiki/Колония_в_космосе',
                #   'http://tardis.fandom.com/ru/wiki/Лицо_зла',
                #   'http://tardis.fandom.com/ru/wiki/Кинда_(ТВ_история)',
                #   'https://tardis.fandom.com/ru/wiki/Землетрясение_(ТВ_история)',
                #   'https://tardis.fandom.com/ru/wiki/Временной_полёт',
                #   'https://tardis.fandom.com/ru/wiki/Время_и_Рани',
                # 'https://tardis.fandom.com/ru/wiki/Роза_(ТВ_история)',
                # 'https://tardis.fandom.com/ru/wiki/Бойся_её',
                # 'https://tardis.fandom.com/ru/wiki/Утопия',
                # 'https://tardis.fandom.com/ru/wiki/Соучастники',
                # 'https://tardis.fandom.com/ru/wiki/Одиннадцатый_час',
                # 'https://tardis.fandom.com/ru/wiki/Пандорика_открывается',
                # 'https://tardis.fandom.com/ru/wiki/Свадьба_Ривер_Сонг',
                # 'https://tardis.fandom.com/ru/wiki/Глубокий_вдох',
                # 'https://tardis.fandom.com/ru/wiki/Вторжение_зайгонов',
                # 'https://tardis.fandom.com/ru/wiki/С_дьявольским_упорством',
                # 'https://tardis.fandom.com/ru/wiki/Пирамида_на_краю_света',
                # 'https://tardis.fandom.com/ru/wiki/Женщина,_которая_упала_на_Землю',
                # 'https://tardis.fandom.com/ru/wiki/Сокрушители',
                'https://tardis.fandom.com/ru/wiki/Канун_далеков',
                ]

    custom_settings={ 'FEED_URI': "temp_results/%(name)s_%(time)s.csv",

                      'FEED_FORMAT': 'csv'}

    def parse(self, response):
        eps_name = response.xpath("//h1[@id='firstHeading']/text()").extract()
        eps_doctor = response.xpath("//div[@data-source='доктор']/div/a/text()").extract()
        eps_comps = response.xpath("//div[@data-source='спутники']/div/a/text()").getall()
        # eps_author = response.xpath("//div[@data-source='автор']/div/a/text()").extract()
        # eps_dir = response.xpath("//div[@data-source='режиссёр']//*").extract()
        eps_prod = response.xpath("//div[@data-source='продюсер']/div/a/text()").extract()
        eps_data = response.xpath("//div[@data-source='дата выхода']/div/text()").extract()
        contents = response.xpath("//div[@class='mw-parser-output']")
        comp_content = [""]
        for p in contents.xpath(".//p//text()"):
            if (p.extract().strip() != ""):
                comp_content[0]+=p.extract().strip() + " "

        # row_data = zip(eps_name, eps_doctor, eps_comps, eps_author, eps_dir, eps_prod, eps_data, comp_content)
        row_data = zip(eps_name, eps_doctor, eps_comps, eps_prod, eps_data, comp_content)

        for item in row_data:
            scrapped_info = {
                'URL': response.url,
                'Name': item[0],
                'Doctor': item[1],
                'Companions': item[2],
                # 'Author': item[3],
                # 'Director': item[3],
                'Producer': item[3],
                'Data': item[4],
                'Content': item[5]
            }
            yield scrapped_info

            next_page = response.xpath("//td[@data-source='следующее']//a/@href").extract()
            np = str(next_page)
            if next_page:
                yield scrapy.Request("http://tardis.fandom.com" + np[2:len(np)-2], callback=self.parse)