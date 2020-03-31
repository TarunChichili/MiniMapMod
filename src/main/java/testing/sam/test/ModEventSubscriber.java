package testing.sam.test;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistryEntry;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)

public class ModEventSubscriber {


    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> Event) {
        Event.getRegistry().registerAll(setup(new Item(new Item.Properties()), "test_item"));

    }

    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> Event) {
        Event.getRegistry().registerAll(
                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)), "test_ore")
        );
    }


    /* Credit For The Two Methods Below : https://cadiboo.github.io/tutorials/1.15.1/forge/1.5-first-item/
      This is the first of only four places in this tutorial in which I will tell you to blindly copy paste code without
      understanding how it works. I don’t expect you to understand how the code in these places works because it deals
      with advanced java concepts like generics and works through very advanced code on Forge’s part that uses ASM and
      Reflection. The following code is a very elegant solution to the problem of having very messy and fragile
      registration code. It uses generics and method overloading to create a method called setup that we will call to
      correctly setup up all our registry entries (Items/Blocks/TileEntitys/EntityEntrys/Dimensions). This method is
      important because there are many (only slightly) wrong ways of setting up registry entries that completely break
      the mod that uses them (and that can also easily break other mods).
     */
    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
        return setup(entry, new ResourceLocation(Main.MODID, name));
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
        entry.setRegistryName(registryName);

        return entry;
    }

}

