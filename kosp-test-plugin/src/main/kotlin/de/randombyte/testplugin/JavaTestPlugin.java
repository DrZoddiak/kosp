/*
package de.randombyte.testplugin;



@Plugin(id = "java-kosp-test-plugin", name = "JavaKospTestPlugin", version = "1.0")
public class JavaTestPlugin {

    @ConfigSerializable
    public static class Config {
        @Setting private int testNumber = 0;
        @Setting private Text testText = Text.of(TextColors.RED, "Red text");
        @Setting private TextTemplate testTextTemplate = UtilsKt.fixedTextTemplateOf(
                Text.of("You won "),
                arg("money").color(TextColors.GOLD),
                arg("currencySymbol"),
                Text.of("!"));

        public Config() {
        }

        // Normally all getters and setters follow here but for this example "direct access" is okay
    }

    private final ConfigManager<Config> configManager;

    @Inject
    public JavaTestPlugin(@DefaultConfig(sharedRoot = true) ConfigurationLoader<CommentedConfigurationNode> configLoader) {
        configManager = new ConfigManager<>(
                configLoader,
                Config.class,
                true,
                true,
                typeSerializerCollection -> null);
    }

    @Listener
    public void onInit(GameInitializationEvent event) {
        Sponge.getCommandManager().register(this, CommandSpec.builder()
                .executor((src, args) -> {
                    Config config = configManager.load();

                    config.testNumber += 7;
                    broadcast(config.testText);
                    broadcast(config.testTextTemplate
                            .apply(ImmutableMap.of("money", 42, "currencySymbol", "$"))
                            .toText());

                    broadcast(UtilsKt.fixedTextTemplateOf(
                            Text.of("You won "),
                            arg("money").color(TextColors.GOLD),
                            arg("currencySymbol"),
                            Text.of("!"))
                            .apply(ImmutableMap.of("money", 42, "currencySymbol", "$"))
                            .toText());


                    configManager.save(config);

                    return CommandResult.success();
                }).build(), "testconfig");
    }

    private void broadcast(Text text) {
        Sponge.getServer().getBroadcastChannel().send(text);
    }
}
*/
