# Maven plugin for HTCPCP support

Have you ever dream of having coffee automatically prepared at the beginning of your builds, so that you can have a well
deserved pause when you stop coding?

**This dream is now a reality** thanks to **htcpcp-maven-plugin**.

If you posses a [HTCPCP](https://www.ietf.org/rfc/rfc2324.txt) capable coffee machine, you can add a plugin to your poms.

This plugin supports the BREW requests (in the form of POST) and thus will start preparing your coffee. You will still 
have to move to your coffee machine to get it.

All HTCPCP coffee enhancements are supported.

## What's in there ?

 - Configuration
 - Using htcpcp-maven-plugin as a HTCPCP Java implementation
 - Limitation and roadmap
 - How to contribute to this repository?
 - Professional support
 - License

## Configuration

Clone the repository and run **mvn clean install**. Then add this plugin : 

```
            <plugin>
                <groupId>com.btellier</groupId>
                <artifactId>htcpcp-maven-plugin</artifactId>
                <version>1.0-SNAPSHOT</version>
                <executions>
                    <!-- Will start pooring coffee when compilation begins -->
                    <execution>
                        <id>startBrew</id>
                        <phase>compile</phase>
                        <goals>
                            <goal>startBrew</goal>
                        </goals>
                    </execution>
                    <!-- Stop poring coffee when testing begin. You can now drink it while mvn proceeds unit testing and
                    other goals -->
                    <execution>
                        <id>stopBrew</id>
                        <phase>install</phase>
                        <goals>
                            <goal>stopBrew</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <fake>true</fake>
                    <!-- Optional
                    Default value to false
                    If true, do not perform the request
                    -->
                    <host>coffee.btellier.com</host>
                    <!-- Compulsory
                    The hostname of your coffee pot
                    -->
                    <port>80</port>
                    <!-- Compulsory
                    The port to be used by HTCPCP protocol
                    -->
                    <syrupType>Vanilla</syrupType>
                    <!-- Optional
                    Case incentive
                    Possible values: Vanilla, Almond, Raspberry, Chocolate
                    -->
                    <milkType>Skim</milkType>
                    <!-- Optional
                    Case incentive
                    Possible values: Skim, Cream, Half-and-half, Whole-milk, Part-Skim, Non-Dairy
                    -->
                    <alcoholType>Whisky</alcoholType>
                    <!-- Optional
                    Case incentive
                    Possible values: Whisky, Rum, Kahlua, Aquavit
                    -->
                    <sweetenerType>any</sweetenerType>
                    <!-- Optional
                    As this parameter is not specified in the RFC, no validation, and no normalization is done.
                    This task is thus server responsibility
                    -->
                    <spiceType>any</spiceType>
                    <!-- Optional
                    As this parameter is not specified in the RFC, no validation, and no normalization is done.
                    This task is thus server responsibility
                    -->
                </configuration>
            </plugin>
```

Note: validation of parameters is done by the client. However options should supported by your HTCPCP coffee pot. Refer 
to manual selled with it for more details concerning supported capabilities.

## Using htcpcp-maven-plugin as a HTCPCP Java implementation

Of course, with this maven plugin, I implemented a Java client for HTCPCP protocol.

Basically, all interactions passes threw **HTCPCPEngine**, which is the concurrency safe driver to interact with one 
HTCPCP server. You can use the **handle** method to send **HTCPCPRequest** to the server.

Examples : 

```
        HTCPCPEngine.server(host, port)
            .handle(StartBrewRequest.builder()
                .syrup(syrupType)
                .milk(milkType)
                .sweetener(sweetenerType)
                .spice(spiceType)
                .alcohol(alcoholType)
                .build());
```

For sending a BREW request with *start* payload.

```
        HTCPCPEngine.server(host, port)
            .handle(new StopBrewRequest());
```

For sending a BREW request with *stop* payload.

## Limitation and roadmap

Today, only the BREW method is implemented. We can imagine implementing the GET method, which, thanks to ubiquitous 
robotics might soon be supported by coffee makers. Support for WHEN is also interesting as it will allow to stop poring 
milk and thus will give more control over coffee making process during compilation.

I was very surprised there were no implementations in Java for a so important protocol like HTCPCP. This language is 
supposed to be mature, and maven is a tool where the only limit is your imagination. I might consider making this HTCPCP
client implementation a standalone project, with full support, along with a HTCPCP proxy server, to promote use of
HTCPCP protocol in the industry. Reach me if interested!

## How to contribute to this repository?

 - You should first open an issue, and then come along with the related pull request.
 - No test please! We all love to find issues in production. (and I need to sell support...)
 - All of your commits must contain a joke. If you have no idea, please STFW like me, but this is the more important point.
 - Replace your Github avatar by a kitten. No joke. Only kittens contributes to this project!
 - You should prove you are under heavy dose of CAAAAAFFFFFFFEEEEEEIIIIINNNN!!!!!! Most drugstore or medical analyses lab 
 will propose you such tests, that must be over 40mg/L of blood (according to [wikipedia](https://www.wikiwand.com/en/Caffeine#Decaffeination), 
 this correspond to victims of acute overdosage). Such a document will serve as a CLA. Please attach it to the PR. This 
 document should of course be renewed for every PR.
 - I will reject contributions of people poring ice in their coffee. This is actually a project for coffee lover, invested
 in this divine missions of giving coffee to developers in the most critical moments...
 
## Professional support

Contact me if you need professional support for HTCPCP compatibility issue with coffee hardware, with this plugin, or 
with this java implementation of HTCPCP protocol.

## License

This project is under the [**Do What The Fuck You Want License**](http://www.wtfpl.net/). You are thus free to do what 
you want with this code.
