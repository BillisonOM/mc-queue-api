# Queue-API Documentation
Import the library jar into your project and on your server for full effects

### Why use this? 
Well there is the the option make random queues via the OtherQueue class, just pass it a QueueEndPoint and it'll do what you want. It provides a simple priority system, with the option to get player join dates to form an elapsed player queue time.

### How to initialize
```java
    private void loadQueues(){
        ServerQueue hub = new ServerQueue("hub", "LOBBY");
        // Optional 
        hub.setCriteria(new ServerQueue.PauseCriteria() {
            @Override
            public boolean pauseEndPoint() { // Pauses any queue players from being sent to the end point
                return bungeeChannel.getPlayerCount("LOBBY").join() >= 200;
            }
        });
        hub.add(player);
        hub.remove(player);
    }
```

Might post my 'friends' system soon idk... 
