package pizzeria;

class Baker implements Runnable {
    private final SynchronizedQueue queue;
    private final SynchronizedQueue storage;
    private final int bakingTime;
    private final boolean free;

    Baker(Pizzeria pizzeria, int experience) {
        this.queue = pizzeria.queue;
        this.storage = pizzeria.storage;
        bakingTime = pizzeria.getTimeConstant() / experience;
        this.free = true;
    }

    @Override
    public void run() {
        try {
            this.free = false;
            if(!queue.canRemove()) {
                this.free = true;
                return;
            }
            int order = queue.remove();
            System.out.println(order + " [started baking]");
            Thread.sleep(bakingTime);
            System.out.println(order + " [baked]");
            storage.add(order);
            this.free = true;
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

}
