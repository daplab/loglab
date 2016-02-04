package ch.daplab.loglab;

import de.svenjacobs.loremipsum.LoremIpsum;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * Created by bperroud on 1/28/16.
 */
public class Spam implements Tool {

    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private Configuration conf;
    private LoremIpsum loremIpsum;
    private boolean run = true;

    public static void main(String[] args) throws Exception {
        ToolRunner.run(new Spam(), args);
    }

    Random r = new Random();

    long i = 0;

    @Override
    public int run(String[] strings) throws Exception {

        this.loremIpsum = new LoremIpsum();

        while (run) {
            i++;
            if (r.nextInt(10000) == 0) {
                Exception e = new RuntimeException("" + i + "|" + loremIpsum.getWords( r.nextInt(200) + 50, r.nextInt(5) + 1 ));
                LOG.error("Exception occured at " + i, e);
            } else {
                LOG.warn("" + i + "|" + loremIpsum.getWords(r.nextInt(200) + 50, r.nextInt(5) + 1));
            }
        }

        return 0;
    }

    @Override
    public void setConf(Configuration configuration) {
        this.conf = configuration;
    }

    @Override
    public Configuration getConf() {
        return conf;
    }
}
