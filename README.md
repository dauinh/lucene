# Lucene Sample Project

### Lucene demo
[Getting Started Doc](https://lucene.apache.org/core/9_9_0/index.html)

1. Download Apache Lucene compressed files (v9.9.1)
2. Setting CLASSPATH

```
export CLASSPATH=$(pwd)/modules/lucene-core-9.9.1.jar:$(pwd)/modules/lucene-queryparser-9.9.1.jar:$(pwd)/modules/lucene-analysis-common-9.9.1.jar:$(pwd)/modules/lucene-demo-9.9.1.jar
```
> Go to `lucene-9.9.1` directory
3. Build index
```
java org.apache.lucene.demo.IndexFiles -docs .
```
4. Search index
```
java org.apache.lucene.demo.SearchFiles
```

### Lucene API
On VSCode, add `lucene-core-9.9.1.jar`, `lucene-queryparser-9.9.1.jar`, `lucene-analysis-common-9.9.1.jar`, `lucene-demo-9.9.1.jar` to Reference Libraries