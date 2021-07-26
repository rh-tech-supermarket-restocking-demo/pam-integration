# PAM Integration

Red Hat Process Automation Manager only provides a REST Web-API. 
So that messages coming through AMQP can be read from the AMQ Broker and sent to PAM to trigger a process, 
we need an integration that consumes from AMQ Broker and forwars messages to PAM.

---

## Deployment

1. Use the OpenShift CLI to login to the cluster and make sure you have selected the correct project you want to deploy the integration into.


2. Deploy application.properties as config map to OpenShift. Before you run the command below, please look into the application.properties file and make sure the correct Broker host is defined.
   ```shell
    oc create configmap pam-integration-amqp --from-file=application.properties
    ```

3. Deploy the Camel K integration.
    ```shell
    kamel run PamIntegration.java --config configmap:pam-integration-amqp
    ```