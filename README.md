# Markov Chain Name Generator 

**Markov Chain Name Generator** is Markov chain based library library for procedural generating of names.
It's Kotlin implementation of library from [MarkovNameGenerator](https://github.com/Tw1ddle/MarkovNameGenerator).

## Usage

It can be used in all JVN languages.

### Maven

```  
   <repositories>
     <repository>
       <id>github</id>
       <name>maven-repository</name>
       <url>https://raw.githubusercontent.com/PavlikPolivka/maven-repository/master/</url>
     </repository>
   </repositories>
   
```

```
    <dependency>
      <groupId>com.ppolivka.namegenerator</groupId>
      <artifactId>markov-chain-name-generator</artifactId>
      <version>1.1</version>
    </dependency>
```
### Gradle

```
repositories { 
    mavenRepo urls: 'https://raw.githubusercontent.com/PavlikPolivka/maven-repository/master/
} 
```

```
dependencies {
    compile 'com.ppolivka.namegenerator:markov-chain-name-generator:1.1'
}
```

### Java

```
Generator generator = new KatzBackoffGenerator(new HashSet<>(Arrays.asList("word1", "word2")) , 2, 0.001f);
generator.generate();
```

### Kotlin

```
var generator: Generator = KatzBackoffGenerator(setOf("word1", "word2"), 2, 0.001f)
generator.generate()
```

### Parameters

- Set of words to teach Markov Chain
- Order, how many characters this chain looks back
- Dirichlet prior, like additive smoothing, increases the probability of any item being picked.


## Contributing
Pull requests should be opened to develop branch.

## Licence
Library is licensed under MIT licence.
