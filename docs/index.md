# ARCADIA Components
> ARCADIA Components Developer Documentation

```
WORKING DRAFT
```

<pre>
EDITORS
<a href="https://github.com/azafeiropoulos">azafeiropoulos</a>
<a href="https://github.com/cparaskeva">cparaskeva</a>
<a href="https://github.com/gtsiolis">gtsiolis</a>
</pre>

## 1 Introduction

Cloud orchestration is a continuous evolving area targeting at the optimal deployment and management of cloud-based services. Aspects related to elasticity, scalability, fault-tolerance and support of distributed functionalities are highly considered. Towards the support of orchestration mechanisms -targeted at specific environments in terms of resource usage and locality aspects-, multiple deployment templates and associated orchestrators have been specified and developed. However, given the highly evolving area of cloud/fog computing combined with the trend for network softwarization, existing approaches have to be evolved and become more holistic in terms of supporting deployment and management of services over virtualized infrastructure.

### 1.1 The project

ARCADIA is designing and implementing a novel development toolkit and orchestrator for the design, development and orchestration of distributed applications over programmable multi-site infrastructure. The main novelty of ARCADIA stems from the adoption of a microservices-based development paradigm along with the development of a deployment and runtime policy-aware orchestrator able to deploy and manage the execution of distributed applications denoted in the form of a service graph. Reconfiguration-by-design principles are adopted and further specified, aiming at optimal execution of the designed services.

ARCADIA's core values include the following:

 - `DISTRIBUTED APPS DEVELOPMENT ENVIRONMENT` Design and develop microservices using ARCADIA libraries. Define app-oriented metrics. Reconfigurable-by-design apps.

 - `POLICY-AWARE ORCHESTRATION` Optimal app deployment using meta-heuristic algorithms. Enable real-time policy enforcement. ETSI NFV compliant.

 - `MULTI DATACENTER EXECUTION MIDDLEWARE` Exploit unikernel stacks. Support multi-IaaS scenarios. OpenStack-ready.

 - `COMPONENT & APPLICATION DISCOVERY` Compose microservices-based applications. Discover. Reuse. Deploy.

### 1.2 Components

The ARCADIA (wrapped) components are the basic building blocks of the platform. They are used to compose distributed applications with set of components that depend on each other. Each component has distinct properties that can be configured before use. Some of them can be used as-is while others require other components to operate. Service Graphs are composed of components operating together as a unit, however being independently orchestratable. These relatively complex structures can range from single-component service graphs to sophisticated complex services.

## 2 Getting started

### 2.1 CREATE

#### 2.1.1 Prerequisites

The project is currently using Docker to realise the component architecture so you should first locate the Docker image that would be able to wrap your component. View all available images from [hub.docker.com](https://hub.docker.com/). It is also possible to create your own Docker image.

You should also define the base image that you want to wrap with the ARCADIA agent (maestro). It is also possible to create your own image.

Docker Engine API v1.24 is supported.

#### 2.1.2 Annotations
Annotations are a form of metadata that provide data about a program that is not part of the program itself. The developer of an application tier should annotate its code with required qualitative and quantitative characteristics according to the context model. Annotations can be included within the source code and be properly translated before building the executable, as well as externally accompany the executable and be properly translated by other components of the architecture during executable’s placement and runtime.

Building a component requires using some mandatory and some optional Java annotations based on [JSR 308](https://jcp.org/en/jsr/detail?id=308).

| Annotation                        | Cardinality Constrain | Optional |
|-----------------------------------|-----------------------|----------|
| `@ArcadiaComponent`               |1..1| False    |
| `@ArcadiaConfigurationParameter`  |0..N| True     |
| `@ArcadiaConfigurationParameters` |0..N| True     |
| `@ArcadiaMetric`                  |0..N| True     |
| `@ArcadiaMetrics`                 |0..N| True     |
| `@DependencyBindingHandler`       |0..N| True     |
| `@DependencyExport`               |0..N| True     |
| `@DependencyExports`              |0..N| True     |
| `@DependencyResolutionHandler`    |0..N| True     |
| ~~`@LifecycleInitialize`~~ (Deprecated) |1..1| False    |
| ~~`@LifecycleStart`~~ (Deprecated) |1..1| False    |
| ~~`@LifecycleStop`~~ (Deprecated) |1..1| False    |

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

#### 2.1.3 Implementation

To build a Java application that is an ARCADIA-ready component you should do the following steps.

##### 2.1.3.1 Implement SPI (Service Provider Interface)

First, add the SPI dependency:

```xml
<dependency>
  <groupId>eu.arcadia.maestro</groupId>
  <artifactId>maestro-spi</artifactId>
  <version>RELEASE</version>
  <scope>provided</scope>
</dependency>
```

`NOTE: You don't have to include the maestro-spi dependency in the compiled jar since the maestro-spi is provided by the ARCADIA agent (maestro).`

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

##### 2.1.3.2 Register the SPI implementation

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

#### 2.1.4 Bundling

...

### 2.2 CONFIGURE

#### 2.2.1 Configuration Parameters

ARCADIA components can be configured during design time upon need. There are three types of configuration parameters that can be modified.

##### 2.2.1.1 Docker Parameters

Docker parameters are eventually used to configure the Docker Engine to be pre-installed in each virtual machine (VM) instantiated during the placement of a service graph. Such parameters can include Docker network settings that will be used by the Docker Agent when starting a container.

For example, to start a MySQL server container you will have to provide a minimum of configuration parameters that include the database root password, the database host, network bindings and more. All these parameters can be set as environment variables passed onto `mysqld`.

##### 2.2.1.2 System Parameters

System parameters are component-related parameters used to define component interdependancies.

For example, a component that uses a MySQL driver to connect to a database should know the database name and the credentials of the database.

##### 2.2.1.3 Agent Parameters

Agent parameters are used by the ARCADIA agent (maestro) to configure internal mechanisms such as the implementation class of the Service Provider Interface (SPI).

#### 2.2.2 Convention over configuration

ARCADIA favors convention over configuration and it has been designed to develop scalable and reconfigurable components as quickly as possible. The following naming conventions are interpreted by the ARCADIA agent (maestro) upon request.

- `ImplementationClassName`: The class name of the API implementation
- `DockerImage`: Docker image name
- `DockerExpose`: Docker exposed port
- `DockerEnvironment`: Docker environment variable
- `SystemProperties`: Component related properties (e.g. Database name, username, password, etc)

#### 2.2.3 Configuration using ARCADIA annotations

The following code can be used as the minimum configuration required to build a MySQL server component.

```java
/**
 * Arcadia Configuration Parameters
 */
@ArcadiaConfigurationParameter(name = "ImplementationClassName", description = "The class name of the API implementation", parametertype = ParameterType.String, defaultvalue = "MySQLMetricsProvider", mutableafterstartup = false)

@ArcadiaConfigurationParameter(name = "DockerImage", description = "Docker image name", parametertype = ParameterType.String, defaultvalue = "mysql", mutableafterstartup = false)

@ArcadiaConfigurationParameter(name = "DockerExpose", description = "Docker expose port", parametertype = ParameterType.String, defaultvalue = "3306", mutableafterstartup = false)

@ArcadiaConfigurationParameter(name = "DockerEnvironment", description = "Docker environment variables", parametertype = ParameterType.String, defaultvalue = "MYSQL_ROOT_PASSWORD=!arcadia!,MYSQL_ROOT_HOST=%", mutableafterstartup = false)

@ArcadiaConfigurationParameter(name = "SystemProperties", description = "Docker environment variables", parametertype = ParameterType.String, defaultvalue = "db_user=root,db_password=******,db_port=3306,db_host=localhost", mutableafterstartup = false)
```

#### 2.2.4 Metrics

The following code can be used to define component metrics such as connections, bytes received and bytes sent.

```java
/**
 * Arcadia Metrics
 */
@ArcadiaMetric(name = "Bytes_received", description = "Number of bytes received", unitofmeasurement = "integer", valuetype = ValueType.Integer, maxvalue = "100000", minvalue = "0", higherisbetter = false)

@ArcadiaMetric(name = "Bytes_sent", description = "Number of bytes sent", unitofmeasurement = "integer", valuetype = ValueType.Integer, maxvalue = "100000", minvalue = "0", higherisbetter = false)

@ArcadiaMetric(name = "Connections", description = "Number of current connection to mysql server", unitofmeasurement = "integer", valuetype = ValueType.Integer, maxvalue = "10000", minvalue = "0", higherisbetter = false)
```

#### 2.2.4 Chainable endpoints

As aforementioned, some components require other components to operate. ARCADIA has defined specific annotations which can be used to define a require or expose functionality.

##### 2.2.4.1 Creating an exposed chainable endpoint

```java
/**
 * Arcadia Dependency Exports
 */
@DependencyExport(CEPCID = "mysqltcp", allowsMultipleTenants = true)
public class WrappedComponent {

 /**
  * Handle the binding
  *
  * @param chainingInfo ChainingInfo object
  */
@DependencyResolutionHandler(CEPCID = "mysqltcp")
    public static void bindedRootComponent(ChainingInfo chainingInfo) {
        logger.info("BINDED COMPONENT:" + chainingInfo.toString());
    }

}
```

##### 2.2.4.1 Creating a required chainable endpoint

```java
/**
 * Arcadia Dependency Exports
 */
@DependencyExport(CEPCID = "mysqltcp", allowsMultipleTenants = true)
public class WrappedComponent {

/**
  * Handle the required binding
  *
  * @param chainingInfo ChainingInfo object
  */
  @DependencyBindingHandler(CEPCID = "transcodingprocessor")
  public static void  bindDependency(ChainingInfo chainingInfo){
    logger.info("BINDED COMPONENT:"+chainingInfo.toString());
    String connectedEndpoint = "http://"+chainingInfo.getPrivateIP()+":"+chainingInfo.getParameterValues().get("port")+"/"+chainingInfo.getParameterValues().get("uri");
    logger.info("Connected Server URI:"+connectedEndpoint);
    System.setProperty("Component.connectedEndpoint",connectedEndpoint);
    }
}
```

### 2.3 UPLOAD

You can upload your (wrapped)

### 2.4 COMPOSE

Service graph composition is performed based on the compatibility of the chainable endpoints. Each component may expose one or more chainable endpoints. These chainable endpoints belong to specific categories. An endpoint is represented by an identifier that is addressed as an Exposed Chainable Endpoint Identifier (ECEPID) while the category per se is represented by a Chainable Endpoint Category Identifier (CEPCID). One CEPCID may correspond to multiple ECEPIDs.

### 2.5 DEPLOY

A service graph grounding contains the actual service graph along with the placement metadata (i.e. resource provider used  per component) and the configuration metadata (i.e. configuration parameters per component). Just like the component and the service graph, the grounded service graph is also defined in XSD.

### 2.6 MONITOR

Each (wrapped) component can expose a number of quantitative metrics that can be grouped in two categories.

System metrics include metrics deriving from the virtualised environment and are considered to be resource related. These metrics include memory usage, CPU payload, network traffic, disk utilization and more. They are pre-defined and exposed by the ARCADIA agent (maestro) via REST endpoints.

Component metrics must be defined by the component developer using ARCADIA metrics annotations (`@ArcadiaMetric`, `@ArcadiaMetrics`). These metrics are also exposed via REST endpoints using the following URL pattern: `/metrics/get/{metric}` where `{metric}` is the name of the metric defined.

You can have a graph representation of each metric from the running application list within the ARCADIA dashboard (`/application/{gsgid}/statistics` where `{gsgid}` if the grounded service graph ID).

## 3. Component Lifecycle

...

## 4. Examples

...
