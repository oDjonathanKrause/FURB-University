from ImageScraper import ImageScraper

# people and classroom images
NEG_IMGS_LINK_1 = 'http://image-net.org/api/text/imagenet.synset.geturls?wnid=n07942152'
NEG_IMGS_LINK_2 = 'http://image-net.org/api/text/imagenet.synset.geturls?wnid=n03038685'

imageScrapper = ImageScraper()
#imageScrapper.restart()
#imageScrapper.store_raw_imgs(NEG_IMGS_LINK_1, 2000)
#imageScrapper.store_raw_imgs(NEG_IMGS_LINK_2, 2000)
#imageScrapper.remove_failed_imgs()
imageScrapper.create_path_info()