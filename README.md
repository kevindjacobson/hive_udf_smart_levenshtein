# Hive UDF library

Modified version of Facebook/Yet-Another-Hive-UDF-Lib Levenshtein function with a max threshold
# Hive UDF's implemented
Levenstein Distance with Max


## Compile

```
mvn compile
```

## Test

```
mvn test
```

## Build
```
mvn assembly:single
```

## Run

```
%> hive
hive> create temporary function smart_levenshtein as 'com.scribd.SmartLevenshtein';
hive> select smart_levenshtein(full_name, first_name, 2) from people limit 10;

```
