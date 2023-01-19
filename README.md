# og-meta-scraper

This little jar has for objective to scrap some urls in order to get their metadatas infos.

It will get the following properties and generate a json file :
* "meta[property='og:title']"
* "meta[property='og:description']"
* "meta[property='og:image']"
* "meta[property='og:url']"

I wanted to create some preview links as you can see in social media application (Twitter, facebook etc...)

You can use it just for one url or for several urls as well

### Scrap one url

Download the jar locally and execute in a command line : ````java -jar og-meta-scraper-<version>-jar-with-dependencies.jar url <url_to_scrap> <output_folder_path>````

### Scrap several urls

if you want to scrap several urls you have to create a CSV file with delimiter ';' and headers id and page

On id column you can put any information you want to retrieve it easily in the final json output
On page column you have to put the url you want to scrap

You can find an example in resources/input/meta.csv

Download the jar locally and execute in a command line : ````java -jar og-meta-scraper-<version>-jar-with-dependencies.jar file <input_file_path> <output_folder_path>````

