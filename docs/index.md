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

The ARCADIA (wrapped) components are the basic building blocks of the platform.They are used to compose distributed applications with set of components that depend on each other. Each component has distinct properties that can be configured before use. Some of them can be used as-is while others require other components to operate. Service Graphs are composed of components operating together as a unit, however being independently orchestratable. These relatively complex structures can range from single-component service graphs to sophisticated complex services.

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

```Java
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

### CONFIGURE

...

### COMPOSE

...

### DEPLOY

...

### MONITOR

...

## Component Lifecycle

...

## Examples

...
