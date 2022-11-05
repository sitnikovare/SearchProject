# scraper.py
import requests
from bs4 import BeautifulSoup

url = 'https://tardis.fandom.com/ru/wiki/Служебная:Все_страницы?from=Питер+Халлидей'
response = requests.get(url)
soup = BeautifulSoup(response.text, 'lxml')
links = soup.find_all('a', class_='mw-redirect')

list_of_links = []
for link in links:
    l = link.get('href')
    full_link = "https://tardis.fandom.com" + l
    list_of_links.append(full_link)


str_l = "["
for l in list_of_links:
  str_l += "\"" + l + "\",\n"
str_l += "]"

with open("links.txt", "w") as file_out:
  file_out.write(str_l)