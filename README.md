Программа для тестирования библиотеки TwoLevelCache

Инструкция по запуску:

**java -jar fileName.jar [Путь к папке файл-кэша] [Размер RAM кэша] [Размер файлового кэша] [Номер стратегии кэша]**

Размеры файлового и RAM кэша задают максимально возможное количество хранимых объектов

Параметр стратегии задаётся в цифрах, реализовано только две стратегии под цифрами:  
1 - Стратегия вытеснения по общему количеству запросов. Вытесняется объект с наименьшим числом запросов за всё время.  
2 - Стратегия вытеснения по времени запроса. Вытесняется объект с самым ранним временем запроса.

Программа принимает команды на вход и выводит состояние файлового и RAM кэша в виде: 

Memory cache:  
[]

File cache:  
[]

Список команд:

put {key} {value}  
get {key}  
update {key} {value}  
delete {key}  
help
