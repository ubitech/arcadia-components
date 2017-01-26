# ARCADIA Components
 `(Working Draft)`
> ARCADIA Components Developer Documentation

Editors: [@azafeiropoulos](https://github.com/azafeiropoulos) [@cparaskeva](https://github.com/cparaskeva) [@gtsiolis](https://github.com/gtsiolis)

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

You should also define a base image for your new virtual machine VM instances that would host the ARCADIA agent (maestro). It is also possible to create your own image.

Docker Engine API v1.24 is supported.

#### 2.1.2 Annotations
Annotations are a form of metadata that provide data about a program that is not part of the program itself. The developer of an application tier should annotate its code with required qualitative and quantitative characteristics according to the context model. Annotations can be included within the source code and be properly translated before building the executable, as well as externally accompany the executable and be properly translated by other components of the architecture during executable’s placement and runtime.

Building a component requires using some mandatory and some optional Java annotations based on [JSR 308](https://jcp.org/en/jsr/detail?id=308).

| Annotation                        | Description | Cardinality Constrain | Optional |
|-----------------------------------|-------------|----------------------|----------|
| `@ArcadiaComponent`               |             | 1..1                 | False    |
| `@ArcadiaConfigurationParameter`  |             | 0..N                 | True     |
| `@ArcadiaConfigurationParameters` |             | 0..N                 | True     |
| `@ArcadiaMetric`                  |             | 0..N                 | True     |
| `@ArcadiaMetrics`                 |             | 0..N                 | True     |
| `@DependencyBindingHandler`       |             | 0..N                 | True     |
| `@DependencyExport`               |             | 0..N                 | True     |
| `@DependencyExports`              |             | 0..N                 | True     |
| `@DependencyResolutionHandler`    |             | 0..N                 | True     |
| ~`@LifecycleInitialize`~             |             | 1..1                 | False    |
| ~`@LifecycleStart`~                 |             | 1..1                 | False    |
| ~`@LifecycleStop`~                  |             | 1..1                 | False    |

#### 2.1.3 Implementation

...

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
