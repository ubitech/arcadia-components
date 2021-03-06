# ARCADIA Components
> ARCADIA Components Developer Documentation

<pre>
EDITORS
<a href="https://github.com/cparaskeva">Christos Paraskeva</a>
<a href="https://github.com/gtsiolis">George Tsiolis</a>
<a href="https://github.com/azafeiropoulos">Anastasios Zafeiropoulos</a>
<a href="https://github.com/giannis20012001">Giannis Tsantilis</a>
<a href="https://github.com/pgouvas">Panagiotis Gouvas</a>
</pre>

<pre>
<a href="#1-introduction">1 Introduction</a>
<a href="#11-the-project">1.1 The project</a>
<a href="#12-components">1.2 Components</a>
<a href="#13-annotations">1.3 Annotations</a>
<a href="#2-getting-started-with-wrapped-components">2 Getting Started with WRAPPED components</a>
<a href="#21-create">2.1 CREATE</a>
<a href="#211-prerequisites">2.1.1 Prerequisites</a>
<a href="#212-implementation">2.1.2 Implementation</a>
<a href="#213-bundling">2.1.3 Bundling</a>
<a href="#22-configure">2.2 CONFIGURE</a>
<a href="#221-configuration-parameters">2.2.1 Configuration Parameters</a>
<a href="#222-container-parameters">2.2.2 Container Parameters</a>
<a href="#3-getting-started-with-native-components">3 Getting Started with NATIVE components</a>
<a href="#31-create">3.1 CREATE</a>
<a href="#311-prerequisites">3.1.1 Prerequisites</a>
<a href="#312-implementation">3.1.2 Implementation</a>
<a href="#313-bundling">3.1.3 Bundling</a>
<a href="#32-configure">3.2 CONFIGURE</a>
<a href="#321-configuration-parameters">3.2.1 Configuration Parameters</a>
<a href="#4-metrics">4 Metrics</a>
<a href="#5-chainable-endpoints">5 Chainable endpoints</a>
<a href="#51-creating-a-chainable-endpoint-exposed-and-required">5.1 Creating a chainable endpoint (exposed and required)</a>
<a href="#52-using-an-exposed-chainable-endpoint">5.2 Using an exposed chainable endpoint</a>
<a href="#53-using-a-required-chainable-endpoint">5.2 Using a required chainable endpoint</a>
<a href="#6-upload">6 UPLOAD</a>
<a href="#7-compose">7 COMPOSE</a>
<a href="#8-deploy">8 DEPLOY</a>
<a href="#9-monitor">9 MONITOR</a>
<a href="#10-component-lifecycle">10 Component Lifecycle</a>
<a href="#11-examples">11 Examples</a>
<a href="#111-wrapped-components">11.1 WRAPPED components</a>
<a href="#112-native-components">11.2 NATIVE components</a>
</pre>

## 1 Introduction

Cloud orchestration is a continuous evolving area targeting at the optimal deployment and management of cloud-based services. Aspects related to elasticity, scalability, fault-tolerance and support of distributed functionalities are highly considered. Towards the support of orchestration mechanisms -targeted at specific environments in terms of resource usage and locality aspects-, multiple deployment templates and associated orchestrators have been specified and developed. However, given the highly evolving area of cloud/fog computing combined with the trend for network softwarization, existing approaches have to be evolved and become more holistic in terms of supporting deployment and management of services over virtualized infrastructure.

### 1.1 The project

ARCADIA is a development and orchestration framework for distributed applications. ARCADIA is coming up with a software development toolkit and an orchestrator. The software development toolkit includes a web-based IDE for the design and development of distributed applications, following a microservices-based development paradigm. Each application is denoted in the form of a service graph consisted of application components (or microservices) chained among each other.

The orchestrator includes a set of intelligent orchestration mechanisms supporting the placement and runtime management of distributed applications over programmable multi-site infrastructure. The set of mechanisms regard an optimisation engine supporting the preparation of optimal deployment plans provided through a constraint satisfaction solver, a runtime policies enforcement engine supporting the real-time adaptation of the deployed applications based on a rules-based management system, a monitoring and analytics extraction engine supporting the extraction of advanced insights and forecasts regarding performance aspects of the deployed applications and a multi-site resource manager supporting management of resources coming from one or multiple infrastructure providers. The intelligent orchestration mechanisms are combined with a dashboard providing a toolset for the visual design of distributed applications in the form of a service graph, an editor for declaring optimization objectives and runtime policies description, as well as a set of custom views regarding the available and active applications and statistics based on their real-time operation. It should be noted that ARCADIA is currently applicable to distributed cloud applications, however it is fully extensible in order to incorporate provision and management of network services -combined with the applications- following the evolution of Software Defined Networking (SDN) and Network Function Virtualization (NFV) technologies.

ARCADIA's core values include the following:

 - `DISTRIBUTED APPS DEVELOPMENT ENVIRONMENT` Design and develop microservices using ARCADIA libraries. Define app-oriented metrics. Reconfigurable-by-design apps.

 - `POLICY-AWARE ORCHESTRATION` Optimal app deployment using meta-heuristic algorithms. Enable real-time policy enforcement. ETSI NFV compliant.

 - `MULTI DATACENTER EXECUTION MIDDLEWARE` Exploit unikernel stacks. Support multi-IaaS scenarios. OpenStack-ready.

 - `COMPONENT & APPLICATION DISCOVERY` Compose microservices-based applications. Discover. Reuse. Deploy.

### 1.2 Components

The ARCADIA components are the basic building blocks of the platform. They are used to compose distributed applications with set of components that depend on each other. Each component has distinct properties that can be configured before use. Some of them can be used as-is while others require other components to operate. Service Graphs are composed of components operating together as a unit, however being independently orchestratable. These relatively complex structures can range from single-component service graphs to sophisticated complex services.

ARCADIA currently supports *wrapped* and *native* components.

A wrapped component is a component that is already developed (in an arbitrary programming framework) and can be chained by another ARCADIA component.  From the Orchestrator point of view, the component has the same behaviour as the native component yet the lifecycle management and the handling of metrics and configuration-parameters are performed by the business logic of the component per-se.

A native component is the component that that is developed solely in Java as an autonomous microservice. To this end, the lifecycle management of the service and the handling of metrics, configuration-parameters and required/exposed interfaces are handled by the microservice itself.

### 1.3 Annotations

Annotations are a form of metadata that provide data about a program that is not part of the program itself. The developer of an application tier should annotate its code with required qualitative and quantitative characteristics according to the context model. Annotations can be included within the source code and be properly translated before building the executable, as well as externally accompany the executable and be properly translated by other components of the architecture during executable’s placement and runtime.

Building a component requires using some mandatory and some optional Java annotations based on [JSR 308](https://jcp.org/en/jsr/detail?id=308).

| Annotation `(Required/Optional)` `(Native/Wrapped)` | Cardinality |
|---------------------------------------------------|----------------|
| `@ArcadiaComponent` `(R)` `(N/W)` | `1..1` |
| `@ArcadiaConfigurationParameter` `(O)` `(N/W)` | `0..N` |
| `@ArcadiaMetric` `(O)` `(N/W)` | `0..N` |
| `@ArcadiaChainableEndpoint` `(O)` `(N/W)` | `0..N` |
| `@ArcadiaChainableEndpointResolutionHandler` `(O)` `(N/W)` | `0..N` |
| `@ArcadiaChainableEndpointBindingHandler` `(O)` `(N/W)` | `0..N` |
| `@ArcadiaBehavioralProfile` `(O)` `(N/W)` | `0..1` |
| `@ArcadiaExecutionRequirement` `(O)` `(N/W)` | `0..1` |
| `@ArcadiaContainerParameter` `(R)` `(W)` | `1..N` |
| `@ArcadiaLifecycleInitialize` `(R)` `(N)` | `1..1` |
| `@ArcadiaLifecycleStar` `(R)` `(N)`| `1..1` |
| `@ArcadiaLifecycleStop` `(R)` `(N)`| `1..1` |

> When using `@ArcadiaChainableEndpoint` annotation you should use `@ArcadiaChainableEndpointResolutionHandler` or `@ArcadiaChainableEndpointBindingHandler` annotation depending whether you are making a component that exposes or requires an endpoint respectively.

In order to be able to use ARCADIA annotations you should first include the following Maven repository:

```xml
<repositories>
  <repository>
    <id>thirdparty</id>
    <url>http://ci.arcadia-framework.eu/nexus/content/repositories/thirdparty</url>
  </repository>
</repositories>
```

Onwards, you should add the ARCADIA annotations dependency:

```xml
<dependency>
  <groupId>eu.arcadia</groupId>
  <artifactId>annotation-libs</artifactId>
  <version>1.0.0.RC1</version>
</dependency>
```

## 2 Getting started with WRAPPED components

### 2.1 CREATE

#### 2.1.1 Prerequisites

The project is using [Docker](https://www.docker.com/) to realise the WRAPPED components so you should first locate the Docker image that would be able to wrap your component. View all available images from [hub.docker.com](https://hub.docker.com/). It is also possible to create your own Docker image.

You should also define the base image that you want to wrap with the ARCADIA agent (maestro). It is also possible to create your own image.

Docker Engine API v1.24 is supported.

#### 2.1.2 Implementation

First, add the SPI (Service Provider Interface) dependency:

```xml
<dependency>
  <groupId>eu.arcadia.maestro</groupId>
  <artifactId>maestro-spi</artifactId>
  <version>RELEASE</version>
  <scope>provided</scope>
</dependency>
```

> NOTE: You don't have to include the maestro-spi dependency in the compiled jar since the maestro-spi is provided by the ARCADIA agent (maestro).

Then, you should implement the `MetricsProvider` interface. `MetricsProvider` is eventually used by the ARCADIA agent to expose all metrics defined in the component configuration.

```java
public interface MetricsProvider {

    /**
     * Fetch an application/service metric given its name
     *
     * @param <T> The parameter type of the metric
     * @param name The name of the metric to retrieve
     * @return The value of the metric in current time
     */
    public <T> T getMetric(String name);

    /**
     * Fetch an application/service metric given its name and type
     *
     * @param <T> The parameter type of the metric
     * @param name The name of the metric to retrieve
     * @param clazz The class which the metric value will be casted
     * @return The value of the metric in current time
     */
    public <T> T getMetric(String name, Class<T> clazz);
}
```

In this step you should register the implementation of the SPI in order to make it discoverable and usable by the ARCADIA agent.

First, the component file (.jar) should contain a class file for each supported service. Following Java's convention, each class should have the name of the newly defined class which is a concrete subclass of one of the abstract service provider classes.

Second, the component file (.jar) must also contain any supporting classes required by the new SPI implementation.

Last but not least, the component should include a file within the `META-INF/services` folder for each new SPI class being subclassed. For example, for the `MetricsProvider` class you should add a file named `eu.arcadia.maestro.api.MetricsProvider`. The file inside should contain the names of the new subclasses:

```
# Provider of metrics services
eu.arcadia.maestro.mysql.impl.MySQLMetricsProvider
```

`NOTE: See more examples at the end of the documentation. Learn more about Service Provider Interfaces (SPI) at  https://docs.oracle.com/javase/tutorial/sound/SPI-intro.html
`

#### 2.1.3 Bundling

Before you can upload the compiled version of the (wrapped) component you should make sure that the compiled file (.jar) include all the required runtime dependencies. The compiled "fat jar" is a file that includes all the dependencies of the Maven project. To create the "fat jar" you will have to use the Maven Assembly Plugin which can be configured as follows:

```xml
<build>
    <plugins>
        <plugin>
            <artifactId>maven-assembly-plugin</artifactId>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>assembly</goal>
                    </goals>
                </execution>
            </executions>
            <configuration>
                <descriptorRefs>
                    <descriptorRef>jar-with-dependencies</descriptorRef>
                </descriptorRefs>
            </configuration>
        </plugin>
    </plugins>
</build>
```

Then, to create a project assembly, simple execute the normal `package` phase from the default lifecycle:

`mvn package`

When this build completes, you should see a file in the target directory with a name similar to the following:

`target/{component}-jar-with-dependencies.jar`

Once you reach here you will have an ARCADIA-ready component which can you can upload, configure and deploy within the ARCADIA platform.

### 2.2 CONFIGURE

#### 2.2.1 Configuration Parameters

Component parameters are used to define component interdependancies. For example, to start a MySQL server container you will have to provide a minimum of configuration parameters that include the database root password, the database host, network bindings and more. All these parameters can be set as environment variables passed onto `mysqld`.

```java
@ArcadiaConfigurationParameter(name = "db_user", description = "The username of the database", parametertype = ParameterType.String, defaultvalue = "root", mutableafterstartup = false)

@ArcadiaConfigurationParameter(name = "db_password", description = "The password of the database user", parametertype = ParameterType.String, defaultvalue = "!arcadia!", mutableafterstartup = false)

@ArcadiaConfigurationParameter(name = "db_port", description = "The port which mysql server is listening", parametertype = ParameterType.String, defaultvalue = "3306", mutableafterstartup = false)

@ArcadiaConfigurationParameter(name = "db_host", description = "The hostname which the mysql server can be reached", parametertype = ParameterType.String, defaultvalue = "localhost", mutableafterstartup = false)
```

#### 2.2.2 Container Parameters

Container parameters are eventually used to configure the Docker Engine to be pre-installed in each virtual machine (VM) instantiated during the placement of a service graph. Such parameters can include Docker network settings that will be used by the Docker Agent when starting a container.

- `DockerImage`: Docker image name (MANDATORY)
- `DockerExpose`: Docke exposed port (OPTIONAL)
- `DockerEnvironment`:Docker environment variable (OPTIONAL)
- `DockerCmd`: Docker arbitary command for the container (OPTIONAL)
- `DockerPrivilege`: Enable/Disable container privilege flag (Default: FALSE)

The following code can be used as the minimum configuration required to build a MySQL server component.

```java
@ArcadiaContainerParameter(key = "DockerImage", value = "mysql", description = "Docker image name")

@ArcadiaContainerParameter(key = "DockerExpose", value = "3306", description = "Docker expose port")

@ArcadiaContainerParameter(key = "DockerEnvironment", value = "MYSQL_ROOT_PASSWORD=!arcadia!,MYSQL_ROOT_HOST=%", description = "Docker environment variables")

@ArcadiaContainerParameter(key = "DockerCmd",   value = "-dbhost %MONGO_DB_HOST%",   description = "Docker added command")

@ArcadiaContainerParameter(key = "DockerPrivilegeflag",value = "true", description = "Enable docker privileged condition"

@ArcadiaContainerParameter(key = "DockerExpose",  value = "139,445" ,  description = "Docker expose port")
 
```

## 3 Getting started with NATIVE components

### 3.1 CREATE

#### 3.1.1 Prerequisites

The project is using [Spring Boot](http://projects.spring.io/spring-boot/) to realise the NATIVE components so make sure that you build your component using Spring Boot. You can geneate a Maven project with Spring Boot using the [Spring Initializr](https://start.spring.io/) or start using Spring Boot by adding the following dependancy:
       
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
  <version>1.5.2.RELEASE</version>
</dependency>
```

#### 3.1.2 Implementation

To build a Java application that is an ARCADIA-ready component you should do the following steps. Once you include the Spring Boot dependancy in your project, you should implement and annotate the following static lifecycle methods that will be later on invoked by the agent (maestro):

```java
@ArcadiaLifecycleInitialize /* for the init() method */
public static void init() {
    System.setProperty("Component.sentRequests", "0");
    System.setProperty("Component.receivedRequests", "0");
    System.setProperty("app.port", NativeApplication.DEFAULT_PORT);
}

@ArcadiaLifecycleStart /* for the start() method */
public static String start() {
    if (appContext == null) {
        appContext = SpringApplication.run(new Class[]{NativeApplication.class, CustomizationBean.class}, new String[]{});
    } else {
        LOGGER.severe("AppContext is not null ! Application is already started !");
    }
    return String.valueOf(appContext.isActive());
}

@ArcadiaLifecycleStop /* for the stop() method */
public static String stop() {
    if (appContext != null) {
        SpringApplication.exit(appContext);
        appContext.close();
        appContext = null;
    } else {
        LOGGER.severe("AppContext is null ! Application has not been started !");
    }
    return String.valueOf((appContext == null));
}
```

> NOTE: Include only one static main function. The main function is never called.

Set `isNative` parameter to `true` within `@ArcadiaComponent` annotation like the following:

```java
@ArcadiaComponent(componentname = "Ping", componentversion = "0.1.0", componentdescription = "Sample arcadia native component which sends ping request", isNative = true, tags = {"ping", "sample"})
```

> Ensure that all lifecycle methods are declared as public and static.

#### 3.1.3 Bundling

Before uploading the compiled version of the NATIVE component, you should make sure that the compiled file (.jar) is build using the Spring Boot Maven plugin. To package the executable jar or war and run an application, add the following plugin in your pom.xml:

```xml
<build>                      
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <executions>
                <execution>
                    <goals>
                        <goal>repackage</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

This configuration will repackage a jar or war that is built during the package phase of the Maven lifecycle.

```
mvn package
```

Once you package your application you can find the executable within the `target` directory. You can then UPLOAD, COMPOSE, DEPLOY and MONITOR this ARCADIA-ready native component.

### 3.2 CONFIGURE

#### 3.2.1 Configuration Parameters

Configuration parameters are used to define component interdependancies.

- `ConfigurationParameter`: Component related properties (e.g. Max requests, Service port, Credentials, etc)

The following code can be used to set the maximum allowed requests handled by a component:

```java
@ArcadiaConfigurationParameter(name = "maxrequests", description = "Max allowed request handlings", parametertype = ParameterType.String, defaultvalue = "10", mutableafterstartup = false)
```

## 4 Metrics

The following code can be used to define component metrics such as connections, bytes received and bytes sent.

```java
/**
 * Arcadia Metrics
 */
@ArcadiaMetric(name = "Bytes_received", description = "Number of bytes received", unitofmeasurement = "integer", valuetype = ValueType.Integer, maxvalue = "100000", minvalue = "0", higherisbetter = false)

@ArcadiaMetric(name = "Bytes_sent", description = "Number of bytes sent", unitofmeasurement = "integer", valuetype = ValueType.Integer, maxvalue = "100000", minvalue = "0", higherisbetter = false)

@ArcadiaMetric(name = "Connections", description = "Number of current connection to mysql server", unitofmeasurement = "integer", valuetype = ValueType.Integer, maxvalue = "10000", minvalue = "0", higherisbetter = false)
```

## 5 Chainable endpoints

As aforementioned, some components require other components to operate. ARCADIA has defined specific annotations which can be used to define a require or expose functionality.

### 5.1 Creating a chainable endpoint (exposed and required)

You can create or edit all chainable endpoint categories and their parameters via the ARCADIA dashboard.

### 5.2 Using an exposed chainable endpoint

```java
/**
 * Arcadia Dependency Exports
 */
@ArcadiaChainableEndpoint(CEPCID = "mysqltcp", allowsMultipleTenants = true)
public class WrappedComponent {

 /**
  * Handle the binding
  *
  * @param chainingInfo ChainingInfo object
  */
@ArcadiaChainableEndpointResolutionHandler(CEPCID = "mysqltcp")
    public static void bindedRootComponent(ChainingInfo chainingInfo) {
        logger.info("BINDED COMPONENT:" + chainingInfo.toString());
    }
}
```

### 5.3 Using a required chainable endpoint

```java
/**
 * Arcadia Dependency Exports
 */
@ArcadiaChainableEndpoint(CEPCID = "mysqltcp", allowsMultipleTenants = true)
public class WrappedComponent {

/**
  * Handle the required binding
  *
  * @param chainingInfo ChainingInfo object
  */
  @ArcadiaChainableEndpointBindingHandler(CEPCID = "transcodingprocessor")
  public static void  bindDependency(ChainingInfo chainingInfo){
    logger.info("BINDED COMPONENT:"+chainingInfo.toString());
    String connectedEndpoint = "http://"+chainingInfo.getPrivateIP()+":"+chainingInfo.getParameterValues().get("port")+"/"+chainingInfo.getParameterValues().get("uri");
    logger.info("Connected Server URI:"+connectedEndpoint);
    System.setProperty("Component.connectedEndpoint",connectedEndpoint);
    }
}
```

## 6 UPLOAD

You can upload your (wrapped) component in two ways. Both ways require an API key generated via the ARCADIA dashboard.

First, you can use the IDE to create a project. Afterwards you can add your API key, validate the project and upload the project.

Second, you can use your own IDE to create a project. Afterwards you can create and configure a component using the aforementioned steps. Once you finish you can upload the compiled file (.jar) of the component via the ARCADIA dashboard.

## 7 COMPOSE

Service graph composition is performed based on the compatibility of the chainable endpoints. Each component may expose one or more chainable endpoints. These chainable endpoints belong to specific categories. An endpoint is represented by an identifier that is addressed as an Exposed Chainable Endpoint Identifier (ECEPID) while the category per se is represented by a Chainable Endpoint Category Identifier (CEPCID). One CEPCID may correspond to multiple ECEPIDs.

## 8 DEPLOY

A service graph grounding contains the actual service graph along with the placement metadata (i.e. resource provider used  per component) and the configuration metadata (i.e. configuration parameters per component). Just like the component and the service graph, the grounded service graph is also defined in XSD.

## 9 MONITOR

Each (wrapped) component can expose a number of quantitative metrics that can be grouped in two categories.

System metrics include metrics deriving from the virtualised environment and are considered to be resource related. These metrics include memory usage, CPU payload, network traffic, disk utilization and more. They are pre-defined and exposed by the ARCADIA agent (maestro) via REST endpoints.

Component metrics must be defined by the component developer using ARCADIA metrics annotations (`@ArcadiaMetric`, `@ArcadiaMetrics`). These metrics are also exposed via REST endpoints using the following URL pattern: `/metrics/get/{metric}` where `{metric}` is the name of the metric defined.

You can have a graph representation of each metric from the running application list within the ARCADIA dashboard (`/application/{gsgid}/statistics` where `{gsgid}` is the grounded service graph ID).

## 10 Component Lifecycle

The following describe the states during the lifecycle of an ARCADIA component.

| State | Description |
|---------------------------------------------------|----------------|
| `BOOTSTRAPPED`| A virtual machine has been spawned and has received the boot command |
| `INITIALIZED` | The agent has established connection with the controller |
| `BLOCKED` | The agent has been interrupted and waiting for all endpoints to become resolved |
| `DEPLOYED` | The agent has complete component information regarding system properties, required endpoints and required container images |
| `STARTED` | The component is up and running |
| `UNDEPLOYED` | The component is not running and configuration has been unset |
| `STOPPED` | The component is not running |
| `ERRONEOUS` | An error has occured during the previous states |

## 11 Examples

### 11.1 WRAPPED Components

- [mongo](https://github.com/ubitech/arcadia-components/tree/master/components/wrapped/mongo)
- [mysql](https://github.com/ubitech/arcadia-components/tree/master/components/wrapped/mysql)
- [orion](https://github.com/ubitech/arcadia-components/tree/master/components/wrapped/orion)
- [phpdashboard](https://github.com/ubitech/arcadia-components/tree/master/components/wrapped/phpdashboard)
- [samba](https://github.com/ubitech/arcadia-components/tree/master/components/wrapped/samba)
- [springbootapp](https://github.com/ubitech/arcadia-components/tree/master/components/wrapped/springbootapp)
- [wordpress](https://github.com/ubitech/arcadia-components/tree/master/components/wrapped/wordpress)

### 11.2 NATIVE Components

- [ping](https://github.com/ubitech/arcadia-components/tree/master/components/native/ping)
- [pong](https://github.com/ubitech/arcadia-components/tree/master/components/native/pong)
