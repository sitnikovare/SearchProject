import scrapy


class GetallpagesSpider(scrapy.Spider):
    name = 'getAllPages'
    allowed_domains = ['tardis.fandom.com']
    start_urls = ["https://tardis.fandom.com/ru/wiki/%D0%9F%D0%BB%D0%B0%D0%BD_%D0%A1%D0%BE%D0%BD%D1%82%D0%B0%D1%80%D0%B0%D0%BD%D1%86%D0%B5%D0%B2",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D0%BB%D0%B0%D0%BD%D0%B5%D1%82%D0%B0_%D0%94%D0%B0%D0%BB%D0%B5%D0%BA%D0%BE%D0%B2",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D0%BB%D0%B0%D0%BD%D0%B5%D1%82%D0%B0_%D0%9C%D0%B5%D1%85%D0%B0%D0%BD%D0%BE%D0%B8%D0%B4%D0%BE%D0%B2",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D0%BB%D0%B0%D0%BD%D0%B5%D1%82%D0%B0_%D0%A3%D0%B4%D0%BE%D0%B2",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D0%BB%D0%B0%D0%BD%D0%B5%D1%82%D1%8B",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D0%BB%D0%B0%D0%BD%D0%B5%D1%82%D1%8B_%D0%B3%D0%B5%D0%BF%D0%B0%D1%80%D0%B4%D0%BE%D0%B2",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D0%BB%D0%B0%D1%87%D1%83%D1%89%D0%B8%D0%B5_%D0%90%D0%BD%D0%B3%D0%B5%D0%BB%D1%8B",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D0%BB%D0%B0%D1%87%D1%83%D1%89%D0%B8%D0%B5_%D0%B0%D0%BD%D0%B3%D0%B5%D0%BB%D1%8B_%E2%80%94_%D1%81%D0%BF%D0%B8%D1%81%D0%BE%D0%BA_%D0%BF%D0%BE%D1%8F%D0%B2%D0%BB%D0%B5%D0%BD%D0%B8%D0%B9",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D0%BB%D0%B0%D1%87%D1%83%D1%89%D0%B8%D0%B9_%D0%90%D0%BD%D0%B3%D0%B5%D0%BB",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D0%BB%D0%B0%D1%89%D0%B0%D0%BD%D0%B8%D1%86%D0%B0_%D1%81%D0%BA%D0%BE%D1%80%D0%B1%D0%B8",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D0%BB%D0%B5%D0%BD%D0%BD%D0%B8%D0%BA_%D0%94%D0%B6%D1%83%D0%B4%D1%83%D0%BD%D0%B0",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D0%BB%D0%BE%D1%85%D0%BE%D0%B9_%D0%B2%D0%BE%D0%BB%D0%BA",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D0%BE%D0%B1%D0%B5%D0%B3_%D0%BE%D1%82_%D0%B3%D1%80%D0%B5%D0%BB",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D0%BE%D0%B1%D0%B5%D0%B4%D0%B0_%D0%94%D0%B0%D0%BB%D0%B5%D0%BA%D0%BE%D0%B2",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D0%BE%D0%B1%D0%B5%D0%B4%D0%B8%D1%82%D0%B5%D0%BB%D0%B8_%D0%BF%D0%BE%D0%BB%D1%83%D1%87%D0%B0%D1%8E%D1%82_%D0%B2%D1%81%D1%91",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D0%BE%D0%B2%D0%B0%D1%80%D1%91%D0%BD%D0%BE%D0%BA",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D0%BE%D0%B2%D0%B5%D0%BB%D0%B8%D1%82%D0%B5%D0%BB%D0%B8_%D0%92%D0%BE%D0%B9%D0%BD%D1%8B",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D0%BE%D0%B2%D0%B5%D0%BB%D0%B8%D1%82%D0%B5%D0%BB%D0%B8_%D0%B2%D1%80%D0%B5%D0%BC%D0%B5%D0%BD%D0%B8",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D0%BE%D0%B2%D0%B5%D0%BB%D0%B8%D1%82%D0%B5%D0%BB%D1%8C_%D0%92%D1%80%D0%B5%D0%BC%D0%B5%D0%BD%D0%B8",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D0%BE%D0%B2%D0%B5%D0%BB%D0%B8%D1%82%D0%B5%D0%BB%D1%8C_%D0%92%D1%80%D0%B5%D0%BC%D0%B5%D0%BD%D0%B8_(%D0%A2%D0%B5%D1%80%D1%80%D0%BE%D1%80_%D0%B0%D0%B2%D1%82%D0%BE%D0%BD%D0%BE%D0%B2)",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D0%BE%D0%B2%D0%B5%D0%BB%D0%B8%D1%82%D0%B5%D0%BB%D1%8C_%D1%81%D0%BD%D0%BE%D0%B2",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D0%BE%D0%B4%D0%B3%D0%BE%D1%82%D0%BE%D0%B2%D0%BB%D0%B5%D0%BD%D0%B8%D1%8F_%D0%BA_%D0%B2%D0%BE%D0%B9%D0%BD%D0%B5",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D0%BE%D0%BB%D0%BA%D0%BE%D0%B2%D0%BD%D0%B8%D0%BA_%D0%91%D0%BB%D1%8E",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D0%BE%D0%BB%D0%BD%D0%BE%D1%87%D1%8C_(%D0%A2%D0%92_%D0%B8%D1%81%D1%82%D0%BE%D1%80%D0%B8%D1%8F)",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D0%BE%D0%BB%D1%83%D0%BB%D0%B8%D0%BA%D0%B8%D0%B9_%D1%87%D0%B5%D0%BB%D0%BE%D0%B2%D0%B5%D0%BA",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D0%BE%D1%80%D1%80%D0%B8%D0%B4%D0%B6",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D0%BE%D1%81%D0%BB%D0%B0%D0%BD%D0%BD%D0%B8%D0%BA_%D1%80%D0%B0%D1%8F",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D0%BE%D1%81%D0%BB%D0%B5%D0%B4%D0%BD%D0%B8%D0%B9_%D1%87%D0%B0%D1%81",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D1%80%D0%B0%D0%B2%D0%B8%D1%82%D0%B5%D0%BB%D0%B8_%D0%92%D1%81%D0%B5%D0%BB%D0%B5%D0%BD%D0%BD%D0%BE%D0%B9",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D1%80%D0%B5%D0%B4%D0%B5%D0%BB",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D1%80%D0%B5%D0%B4%D0%B5%D0%BB_(%D0%91%D0%B5%D0%B7_%D0%B3%D1%80%D0%B0%D0%BD%D0%B8%D1%86)",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D1%80%D0%B5%D0%B9%D1%81_(%D0%A2%D1%80%D0%B5%D1%82%D1%8C%D1%8F_%D0%BC%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D1%8F_%D0%B2%D0%BE%D0%B9%D0%BD%D0%B0)",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D1%80%D0%B5%D0%BE%D0%B1%D1%80%D0%B0%D0%B7%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5_(%D0%B0%D1%83%D0%B4%D0%B8%D0%BE_%D0%B8%D1%81%D1%82%D0%BE%D1%80%D0%B8%D1%8F)",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D1%80%D0%B8%D0%B1%D0%B5%D0%B6%D0%B8%D1%89%D0%B5_%D0%94%D0%B0%D0%BB%D0%B5%D0%BA%D0%BE%D0%B2",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D1%80%D0%B8%D0%B7%D1%80%D0%B0%D0%BA_%D0%B1%D0%BE%D0%BB%D0%BE%D1%82_%D0%9B%D0%B0%D0%BD%D0%B8%D0%BE%D0%BD",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D1%80%D0%B8%D0%BA%D0%BB%D1%8E%D1%87%D0%B5%D0%BD%D0%B8%D0%B5_%D0%B2_%D0%BF%D1%80%D0%BE%D1%81%D1%82%D1%80%D0%B0%D0%BD%D1%81%D1%82%D0%B2%D0%B5_%D0%B8_%D0%B2%D1%80%D0%B5%D0%BC%D0%B5%D0%BD%D0%B8_(%D0%A2%D0%92_%D0%B8%D1%81%D1%82%D0%BE%D1%80%D0%B8%D1%8F)",
"https://tardis.fandom.com/ru/wiki/%D0%9F%D1%80%D0%B8%D0%BA%D0%BB%D1%8E%D1%87%D0%B5%D0%BD%D0%B8%D1%8F_%D0%92%D0%BE%D1%81%D1%8C%D0%BC%D0%BE%D0%B3%D0%BE_%D0%94%D0%BE%D0%BA%D1%82%D0%BE%D1%80%D0%B0_(Big_Finish)",
]

    custom_settings={ 'FEED_URI': "temp_results/%(name)s_%(time)s.csv",

                      'FEED_FORMAT': 'csv'}

    def parse(self, response):
        header = response.xpath("//h1[@id='firstHeading']/text()").extract()
        contents = response.xpath("//div[@class='mw-parser-output']")
        comp_content = [""]
        for p in contents.xpath(".//p//text()"):
            if (p.extract().strip() != ""):
                comp_content[0]+=p.extract().strip() + " "

        row_data = zip(header, comp_content)

        for item in row_data:
            scrapped_info = {
                'URL': response.url,
                'Name': item[0],
                'Content': item[1]
            }
            yield scrapped_info
