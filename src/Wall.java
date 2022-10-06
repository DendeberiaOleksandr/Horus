import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Wall implements Structure{

    private List<Block> blocks;

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return blocks.stream()
                .filter(block -> block.getColor().equals(color))
                .findFirst();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return blocks.stream()
                .filter(block -> block.getMaterial().equals(material))
                .collect(Collectors.toList());
    }

    @Override
    public int count() {
        List<Block> nonCompositeBlocks = blocks;

        while (nonCompositeBlocks.stream().anyMatch(block -> block instanceof CompositeBlock)){
            List<Block> nextLevelOfNonCompositeBlocks = new ArrayList<>();

            for (Block block : nonCompositeBlocks){
                if (block instanceof CompositeBlock){
                    nextLevelOfNonCompositeBlocks.addAll(((CompositeBlock) block).getBlocks());
                } else {
                    nextLevelOfNonCompositeBlocks.add(block);
                }
            }

            nonCompositeBlocks = nextLevelOfNonCompositeBlocks;
        }

        return nonCompositeBlocks.size();
    }
}
