import scrapy

class CompanionsSpider(scrapy.Spider):
    name = 'companions'
    allowed_domains = ['tardis.fandom.com']
    start_urls = ['http://tardis.fandom.com/ru/wiki/Сьюзан_Форман',
                  'http://tardis.fandom.com/ru/wiki/Барбара_Райт',
                  'http://tardis.fandom.com/ru/wiki/Йен_Честертон',
                  'http://tardis.fandom.com/ru/wiki/Вики_Паллистер',
                  'http://tardis.fandom.com/ru/wiki/Стивен_Тейлор',
                  'http://tardis.fandom.com/ru/wiki/Катарина',
                  'http://tardis.fandom.com/ru/wiki/Сара_Кингдом',
                  'http://tardis.fandom.com/ru/wiki/Додо_Чаплет',
                  'http://tardis.fandom.com/ru/wiki/Полли_Райт',
                  'http://tardis.fandom.com/ru/wiki/Бен_Джексон',
                  'https://tardis.fandom.com/ru/wiki/Полли_Райт',
                  'https://tardis.fandom.com/ru/wiki/Джейми_МакКриммон',
                  'https://tardis.fandom.com/ru/wiki/Виктория_Уотерфилд',
                  'https://tardis.fandom.com/ru/wiki/Зои_Хериот',
                  'https://tardis.fandom.com/ru/wiki/Алистер_Гордон_Летбридж-Стюарт',
                  'https://tardis.fandom.com/ru/wiki/Лиз_Шоу',
                  'https://tardis.fandom.com/ru/wiki/Джон_Бентон',
                  'https://tardis.fandom.com/ru/wiki/Джо_Грант',
                  'https://tardis.fandom.com/ru/wiki/Майк_Йейтс',
                  'https://tardis.fandom.com/ru/wiki/Сара_Джейн_Смит',
                  'https://tardis.fandom.com/ru/wiki/Гарри_Салливан',
                  'https://tardis.fandom.com/ru/wiki/Лила',
                  'https://tardis.fandom.com/ru/wiki/К9_(модель_I)',
                  'https://tardis.fandom.com/ru/wiki/Романа_I',
                  'https://tardis.fandom.com/ru/wiki/К9_(модель_II)',
                  'https://tardis.fandom.com/ru/wiki/Романа_II',
                  'https://tardis.fandom.com/ru/wiki/Адрик',
                  'https://tardis.fandom.com/ru/wiki/Нисса',
                  'https://tardis.fandom.com/ru/wiki/Тиган_Джованка',
                  'https://tardis.fandom.com/ru/wiki/Вислор_Турлоу',
                  'https://tardis.fandom.com/ru/wiki/Камелион',
                  'https://tardis.fandom.com/ru/wiki/Пери_Браун',
                  'https://tardis.fandom.com/ru/wiki/Мелани_Буш',
                  'https://tardis.fandom.com/ru/wiki/Эйс',
                  'https://tardis.fandom.com/ru/wiki/Роза_Тайлер',
                  'https://tardis.fandom.com/ru/wiki/Джек_Харкнесс',
                  'https://tardis.fandom.com/ru/wiki/Микки_Смит',
                  'https://tardis.fandom.com/ru/wiki/Марта_Джонс',
                  'https://tardis.fandom.com/ru/wiki/Донна_Ноубл',
                  'https://tardis.fandom.com/ru/wiki/Эми_Понд',
                  'https://tardis.fandom.com/ru/wiki/Рори_Уильямс',
                  'https://tardis.fandom.com/ru/wiki/Ривер_Сонг',
                  'https://tardis.fandom.com/ru/wiki/Клара_Освальд',
                  'https://tardis.fandom.com/ru/wiki/Нардол',
                  'https://tardis.fandom.com/ru/wiki/Билл_Поттс',
                  'https://tardis.fandom.com/ru/wiki/Мастер',
                  'https://tardis.fandom.com/ru/wiki/Грэм_О%27Брайен',
                  'https://tardis.fandom.com/ru/wiki/Райан_Синклер',
                  'https://tardis.fandom.com/ru/wiki/Ясмин_Кан',
                ]

    custom_settings={ 'FEED_URI': "temp_results/%(name)s_%(time)s.csv",
                      'FEED_FORMAT': 'csv'}

    def parse(self, response):
        comp_name = response.xpath("//h1[@id='firstHeading']/text()").extract()
        comp_nick = response.xpath("//div[@data-source='прозвища']/div/text()").extract()
        comp_race = response.xpath("//div[@data-source='раса']/div/a/text()").extract()
        comp_planet = response.xpath("//div[@data-source='планета']/div/a/text()").extract()
        contents = response.xpath("//div[@class='mw-parser-output']")
        comp_content = [""]
        for p in contents.xpath(".//p//text()"):
            if (p.extract().strip() != ""):
                comp_content[0]+=p.extract().strip() + " "

        row_data = zip(comp_name, comp_nick, comp_race, comp_planet, comp_content)

        for item in row_data:
            scrapped_info = {
                'URL': response.url,
                'Name': item[0],
                'Nicknames': item[1],
                'Race': item[2],
                'Planet': item[3],
                'Content': item[4],
            }
            yield scrapped_info